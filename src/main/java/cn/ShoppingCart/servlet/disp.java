package cn.ShoppingCart.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import cn.ShoppingCart.connection.DbCon;
import cn.ShoppingCart.dao.OrderDao;
import cn.ShoppingCart.dao.ProductDao;
import cn.ShoppingCart.dao.UserDao;

/**
 * Servlet implementation class disp
 */
public class disp extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */

	public disp() {
		super();
		// TODO Auto-generated constructor stub
	}

	int NProduit, NOrder, NUser = 5;
	String chart = "";

	@Override
	public void init() throws ServletException {
		// TODO Auto-generated method stub
		super.init();

		Connection conn = null;
		try {
			conn = new DbCon().getConnection();
		} catch (ClassNotFoundException | SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		ProductDao p = new ProductDao(conn);

		try {
			chart = p.getchart();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		NProduit = p.getTotalNumberOfProducts();
		try {
			NUser = new UserDao(conn).getTotalUsers();
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		NOrder = new OrderDao(conn).getTotalOrders();
		System.out.print(NProduit + " " + NUser + " " + NOrder);
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		request.setAttribute("NProduit", NProduit);
		request.setAttribute("NUser", NUser);
		request.setAttribute("NOrder", NOrder);
		request.setAttribute("chart", chart);

		this.getServletContext().getRequestDispatcher("/first.jsp").forward(request, response);

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
