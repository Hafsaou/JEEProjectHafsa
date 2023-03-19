package cn.ShoppingCart.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;



import cn.ShoppingCart.connection.DbCon;
import cn.ShoppingCart.dao.UserDao;
import cn.ShoppingCart.model.User;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/user-login")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	response.sendRedirect("login.jsp");
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("text/html");
		
		try(PrintWriter out =response.getWriter()){
			String email=request.getParameter("login-email");
			String password=request.getParameter("login-password");
//			System.out.println(email+" "+password);
			
			try {
				
				UserDao udao= new UserDao(DbCon.getConnection());
				User user=udao.userLogin(email, password);
				System.out.println("inside mychecker"+user);
				if(user !=null) {
					System.out.println("User Login Successfuly");
					request.getSession().setAttribute("auth", user);
					response.sendRedirect("index.jsp");
				}else {
					out.println("User Login Failed");
					
				}
			} catch (ClassNotFoundException | SQLException e) {
				e.printStackTrace();
			}
		
		}
	}

}
