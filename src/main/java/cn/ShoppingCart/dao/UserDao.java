package cn.ShoppingCart.dao;

import java.sql.*;

import cn.ShoppingCart.connection.DbCon;
import cn.ShoppingCart.model.Order;
import cn.ShoppingCart.model.User;


public class UserDao {
	private Connection con =null;

	private String query;
    private PreparedStatement pst;
    

	public UserDao(Connection con) {
		this.con = con;
	}
	
	public User userLogin(String email, String password) {
		User user = null;
        try {
            query = "select * from users where email=? and password=?";
            pst = con.prepareStatement(query);
            pst.setString(1, email);
            pst.setString(2, password);
             ResultSet rs = pst.executeQuery();
            if(rs.next()){
            	user = new User();
            	user.setId(rs.getInt("id"));
            	user.setName(rs.getString("name"));
            	user.setEmail(rs.getString("email"));
            }
        } catch (SQLException e) {
            System.out.print(e.getMessage());
        }
        return user;
    }
	

public int getTotalUsers() throws SQLException, ClassNotFoundException {
	
    int total = 0;
    PreparedStatement stmt = null;
    ResultSet rs = null;

        stmt = con.prepareStatement("SELECT COUNT(*) as total_users FROM users");
        rs = stmt.executeQuery();

        if (rs.next()) {
            total = rs.getInt("total_users");
        }
    

    return total;

}
	
		
}
