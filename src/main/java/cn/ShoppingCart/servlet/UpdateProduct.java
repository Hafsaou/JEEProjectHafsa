package cn.ShoppingCart.servlet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Objects;

import cn.ShoppingCart.connection.DbCon;
import cn.ShoppingCart.dao.ProductDao;
import cn.ShoppingCart.model.Product;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class UpdateProduct
 */

public class UpdateProduct extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UpdateProduct() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		  
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */ 
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
	if(!Objects.isNull(request.getParameter("id2"))) {		
		 try {
			ProductDao pd= new ProductDao(DbCon.getConnection());
			Product newProduct=new Product(Integer.valueOf(request.getParameter("id2")),request.getParameter("name"),request.getParameter("category"),Double.valueOf(request.getParameter("price")),request.getParameter("image"));
			pd.updateProduct(newProduct.getId(),newProduct);
		    response.sendRedirect("ListProduct.jsp");	
		}   catch (ClassNotFoundException | SQLException e) { 
			e.printStackTrace();
		}		  	
			}			
	else {		 
		int id=Integer.valueOf(request.getParameter("id"));
			 Product product=null;
			 ProductDao p;       
			try {
				p = new ProductDao(DbCon.getConnection());
				 product=p.findProductById(id);
				 request.setAttribute("product", product);
				 this.getServletContext().getRequestDispatcher("/UpdateProductForm.jsp").forward(request,response);
			} catch (ClassNotFoundException | SQLException e) {
				e.printStackTrace();
			}	 
		}
}
	
	
	

}
