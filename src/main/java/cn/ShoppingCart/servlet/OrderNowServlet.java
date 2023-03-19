package cn.ShoppingCart.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.text.SimpleDateFormat;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import cn.ShoppingCart.connection.DbCon;
import cn.ShoppingCart.dao.OrderDao;
import cn.ShoppingCart.model.Cart;
import cn.ShoppingCart.model.Order;
import cn.ShoppingCart.model.User;
import jakarta.servlet.annotation.WebServlet;

/**
 * Servlet implementation class OrderNowServlet
 */
@WebServlet("/order-now")
public class OrderNowServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public OrderNowServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub

		try (PrintWriter out=response.getWriter()){
//			out.print("this buy now servlet");
			
			SimpleDateFormat formatter=new SimpleDateFormat("yyyy-MM-dd");
//		
			Date date=new Date();
//			
//			
			User auth=(User)request.getSession().getAttribute("auth");
			if(auth!=null) {

				String productId=request.getParameter("id");
				int productQuantity=Integer.parseInt(request.getParameter("quantity"));
//				out.print("this buy now servlet"+" ID: "+productId+" Quantity: "+productQuantity);

				if(productQuantity<=0) {
					productQuantity=1;
				}
//				 
				Order orderModel=new Order();
				orderModel.setId(Integer.parseInt(productId));
				orderModel.setUid(auth.getId());
				orderModel.setQuantity(productQuantity);
				orderModel.setDate(formatter.format(date)); 
				
				OrderDao orderDao=new OrderDao(DbCon.getConnection());
				boolean result=orderDao.insertOrder(orderModel);
			
				if(result){
					ArrayList<Cart> cart_list=(ArrayList<Cart>) request.getSession().getAttribute("cart-list");
					if(cart_list !=null) {
						for(Cart c:cart_list) {
							if(c.getId()==Integer.parseInt(productId)) {
								cart_list.remove(cart_list.indexOf(c));
								break;
							}
						}
					}
					response.sendRedirect("orders.jsp");

				}else {
					out.println("order failed");
				}
//			
//				
			}else {
				response.sendRedirect("login.jsp");
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
