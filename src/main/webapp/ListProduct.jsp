<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import = "cn.ShoppingCart.connection.DbCon" %>
<%@page import ="java.util.*"%>
<%@ page import = "cn.ShoppingCart.dao.ProductDao" %>
<%@page import="cn.ShoppingCart.model.*"%>
 
   
<%
 
ProductDao pd= new ProductDao(DbCon.getConnection());
List<Product> products=pd.getAllProducts();

%>
 
 
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Product List</title>
    <link href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" rel="stylesheet" type="text/css">
<!--     <link rel="apple-touch-icon" sizes="76x76" -->
<!-- 	href="./assets/img/apple-icon.png"> -->
<!-- <link rel="icon" type="image/png" href="./assets/img/favicon.png"> -->
<!-- <title>DashBoard</title> -->
<!--     Fonts and icons     --> 
<!-- <link rel="stylesheet" type="text/css" -->
<!-- 	href="https://fonts.googleapis.com/css?family=Roboto:300,400,500,700,900|Roboto+Slab:400,700" /> -->
<!-- <!-- Nucleo Icons --> 
<!-- <link href="./assets/css/nucleo-icons.css" rel="stylesheet" /> -->
<!-- <link href="./assets/css/nucleo-svg.css" rel="stylesheet" /> -->
<!--  Font Awesome Icons -->
<!-- <script src="https://kit.fontawesome.com/42d5adcbca.js" -->
<!-- 	crossorigin="anonymous"></script> -->
<!--  Material Icons --> 
<!-- <link -->
<!-- 	href="https://fonts.googleapis.com/icon?family=Material+Icons+Round" -->
<!-- 	rel="stylesheet"> -->
<!-- CSS Files --> 
<!-- <link id="pagestyle" href="./assets/css/material-dashboard.css?v=3.0.0" -->
<!-- 	rel="stylesheet" /> -->
</head>
<body    >
<nav
			class="navbar navbar-main navbar-expand-lg px-0 mx-4 shadow-none border-radius-xl"
			id="navbarBlur" navbar-scroll="true">
			<div class="container-fluid py-1 px-3">
			
				 
				<div class="collapse navbar-collapse mt-sm-0 mt-2 me-md-0 me-sm-4"
					id="navbar">
					
					<a class="nav-link text-white "
					href="./ListProduct.jsp"    >
						<div class="text-white text-center me-2 d-flex align-items-center justify-content-center">
						</div> 
						<span class="nav-link-text class="nav-item" ms-1">  
						<h6 style="font-size: 2.8em;">Tables</h6> 
						</span>
				    </a>
					 		 
				</div>
			</div>
		</nav> 
    <div  bgcolor="#7b809a" class="container">
<div style="text-align:center;">
  <h1  style=" color: black;">Product List</h1>
</div>
        <table class="table table-striped">
            <thead>
                <tr>
                    <th>ID</th>
                    <th>Name</th>
                    <th>Category</th>
                    <th>Price</th>
                     
            </thead>
            <tbody>
            <%
if(!products.isEmpty()){
	for(Product p:products){%>
		 
		   <tr>
                        <td><%=p.getId() %></td>
                        <td><%=p.getName() %></td>
                        <td><%=p.getCategory() %></td>
                        <td><%=p.getPrice() %></td>
                        <td><img src="product-image/<%=p.getImage()%>" width="50px" height="50px"></td>
                        <td>
                            <form action="UpdateProduct" method="post" style="display: inline;">
                                <input type="hidden" name="id" value="<%=p.getId() %>">
                                <button type="submit" class="btn btn-primary">Edit</button>
                            </form>
                            <form action="del" method="post" style="display: inline;">
                                <input type="hidden" name="id" value="<%=p.getId() %>">
                                <button type="submit" class="btn btn-danger">Delete</button>
                            </form>
                        </td>
                    </tr>
		
	<% }
	
}

%>
             
                
            </tbody>
        </table>
        <a href="AddProductForm.jsp" class="btn btn-success">Add Product</a>
    </div>
</body>
</html>
