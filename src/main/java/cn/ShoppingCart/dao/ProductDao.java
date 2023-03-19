package cn.ShoppingCart.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import cn.ShoppingCart.model.Cart;
import cn.ShoppingCart.model.Product;


public class ProductDao {
	private Connection con;

	private String query;
	private PreparedStatement pst;
	private ResultSet rs;

	public ProductDao(Connection con) {
		super();
		this.con = con;
	}

	public List<Product> getAllProducts() {
		List<Product> products = new ArrayList<Product>();
		try {

			query = "select * from products";
			pst = this.con.prepareStatement(query);
			rs = pst.executeQuery();

			while (rs.next()) {
				Product row = new Product();
				row.setId(rs.getInt("id"));
				row.setName(rs.getString("name"));
				row.setCategory(rs.getString("category"));
				row.setPrice(rs.getDouble("price"));
				row.setImage(rs.getString("image"));

				products.add(row);
			}

		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
		}
		return products;
	}

	public List<Cart> getCartProducts(ArrayList<Cart> cartList) {
		List<Cart> products = new ArrayList<Cart>();
		try {
			if (cartList.size() > 0) {
				for (Cart item : cartList) {
					query = "select * from products where id=?";
					pst = this.con.prepareStatement(query);
					pst.setInt(1, item.getId());
					rs = pst.executeQuery();

					while (rs.next()) {
						Cart row = new Cart();
						row.setId(rs.getInt("id"));
						row.setName(rs.getString("name"));
						row.setCategory(rs.getString("category"));
						row.setPrice(rs.getDouble("price") * item.getQuantity());
						row.setQuantity(item.getQuantity());
						products.add(row);
					}

				}
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
		return products;

	}

	public double getTotalCartPrice(ArrayList<Cart> cartList) {
		double sum = 0;
		try {
			if (cartList.size() > 0) {
				for (Cart item : cartList) {
					query = "select price from products where id=?";
					pst = this.con.prepareStatement(query);
					pst.setInt(1, item.getId());
					rs = pst.executeQuery();
					while (rs.next()) {
						sum += rs.getDouble("price") * item.getQuantity();
					}

				}
			}

		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
		}
		return sum;
	}

	public void deleteProduct(int productId) throws SQLException {

		pst = con.prepareStatement("DELETE FROM products WHERE id = ?");
		pst.setInt(1, productId);
		pst.executeUpdate();

	}

	public Product findProductById(int productId) throws SQLException {

		Product product = null;

		pst = con.prepareStatement("SELECT * FROM products WHERE id = ?");
		pst.setInt(1, productId);
		rs = pst.executeQuery();

		if (rs.next()) {
			product = new Product();
			product.setId(rs.getInt("id"));
			product.setName(rs.getString("name"));
			product.setCategory(rs.getString("category"));
			product.setPrice(rs.getDouble("price"));
			product.setImage(rs.getString("image"));

		}
		return product;

	}

	public List<String> getAllCategories() {
		List<String> categories = new ArrayList<>();
		try {
			Statement stmt = con.createStatement();
			String sql = "SELECT DISTINCT name FROM Categories";
			rs = stmt.executeQuery(sql);
			while (rs.next()) {
				categories.add(rs.getString("name"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return categories;
	}

	public void updateProduct(int id, Product product) throws SQLException {

		PreparedStatement stmt = null;

		if (product.getImage().equals("")) {
			String sql = "UPDATE products SET name=?, category=?, price=? WHERE id=?";
			stmt = con.prepareStatement(sql);
			stmt.setString(1, product.getName());
			stmt.setString(2, product.getCategory());
			stmt.setDouble(3, product.getPrice());
			stmt.setInt(4, id);
			stmt.executeUpdate();

		} else {
			String sql = "UPDATE products SET name=?, category=?, price=?, image=? WHERE id=?";
			stmt = con.prepareStatement(sql);
			stmt.setString(1, product.getName());
			stmt.setString(2, product.getCategory());
			stmt.setDouble(3, product.getPrice());
			stmt.setString(4, product.getImage());
			stmt.setInt(5, id);
			stmt.executeUpdate();
		}

	}

	public String getchart() throws SQLException {
		Statement stmt = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
		Statement stmt2 = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);

		rs = stmt.executeQuery(
				"SELECT  DATE_FORMAT(o_date, '%Y/%m') AS month, p.category, SUM(o_quantity) AS total_quantity FROM orders o,products p where o.p_id=p.id GROUP BY month, p.category");

		// Create the JSON string
		StringBuilder jsonData = new StringBuilder();
		jsonData.append("[['Month'");
		ResultSet categories = stmt2.executeQuery("SELECT DISTINCT  name FROM categories ORDER BY name");
		while (categories.next()) {
			jsonData.append(",'" + categories.getString("name") + "'");
		}
		jsonData.append(",'Average']");

		while (rs.next()) {
			jsonData.append(",\n['" + rs.getString("month") + "'");
			categories.beforeFirst();
			double total = 0;
			int count = 0;

			while (categories.next()) {
				if (categories.getString("name").equals(rs.getString("p.category"))) {
					jsonData.append("," + rs.getDouble("total_quantity"));
					total += rs.getDouble("total_quantity");
					count++;

				} else {
					jsonData.append(",0");
				}
			}
			double average = count > 0 ? total / count : 0;
			jsonData.append("," + average + "]");
		}

		// Use the JSON string to create the data table
		return "google.visualization.arrayToDataTable(" + jsonData.toString() + "]);";

	}


	
 

	public int getTotalNumberOfProducts() {
	    int totalProducts = 0;
	    try {
	        
	        // Create a statement to execute the query
	        Statement stmt = con.createStatement();
	        
	        // Execute the query and get the result set
	        ResultSet rs = stmt.executeQuery("SELECT COUNT(*) FROM products");
	        
	        // Extract the total number of products from the result set
	        if (rs.next()) {
	            totalProducts = rs.getInt(1);
	        }
	        
	        
	    } catch (SQLException ex) {
	        ex.printStackTrace();
	    }
	    return totalProducts;
	}


}
