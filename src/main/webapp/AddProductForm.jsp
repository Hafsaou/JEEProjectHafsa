<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@page import="cn.ShoppingCart.connection.DbCon"%>
<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
 <%@page import ="java.util.*"%>
<%@page import="cn.ShoppingCart.model.*"%>
<%@page import="cn.ShoppingCart.*"%>
<%@page import="cn.ShoppingCart.dao.ProductDao"%>
<% 
  ProductDao pd= new ProductDao(DbCon.getConnection());
  List<String>  categories=pd.getAllCategories();
%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8">
	<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
	<title>Add Product</title>
	<!-- Bootstrap CSS -->
	<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap/5.1.3/css/bootstrap.min.css">
	<!-- Custom CSS -->
	<style>
		body {
			background-color: #F5F5F5;
			font-family: 'Roboto', sans-serif;
		}
		h1 {
			color: #555;
			text-align: center;
			margin-top: 50px;
			margin-bottom: 30px;
		}
		.container {
			max-width: 500px;
			margin: auto;
			background-color: #fff;
			padding: 30px;
			border-radius: 5px;
			box-shadow: 0 5px 15px rgba(0,0,0,0.3);
		}
		label {
			font-weight: 500;
			color: #555;
			margin-bottom: 5px;
		}
		input {
			border: none;
			border-radius: 0;
			border-bottom: 2px solid #ddd;
			padding: 10px 0;
			font-size: 16px;
			font-weight: 400;
			color: #555;
			background-color: transparent;
			transition: border-bottom-color 0.2s ease-in-out;
		}
		input:focus {
			outline: none;
			border-bottom-color: #007bff;
		}
		button[type="submit"] {
			background-color: #007bff;
			border: none;
			border-radius: 25px;
			padding: 10px 30px;
			color: #fff;
			font-size: 16px;
			font-weight: 500;
			text-transform: uppercase;
			letter-spacing: 1px;
			transition: background-color 0.2s ease-in-out;
			margin-top: 20px;
			margin-bottom: 10px;
			display: block;
			margin-left: auto;
			margin-right: auto;
		}
		button[type="submit"]:hover {
			background-color: #0056b3;
			cursor: pointer;
		}
	</style>
</head>
<body>
	<div class="container">
		<h1>Add Product</h1>
		<form action="AddProduct" method="post" enctype="multipart/form-data">
			<div class="mb-3">
				<label for="name" class="form-label">Name</label>
				<input type="text" class="form-control" id="name" name="name" required>
			</div>
			<div class="form-group">
        <label for="category">Select a category:</label>
        <select class="form-control" id="category" name="category">
            <%    
  if(!categories.isEmpty()){
       for(String category : categories) {
		    {
             %>
		     <option value="<%= category%>"><%= category%></option>
		     <% 
		    }
         }     
  }
  
  else 
  %>
        </select>
    </div>
			<div class="mb-3">
				<label for="price" class="form-label">Price </label>
				<input type="number" class="form-control" id="price" name="price" step="0.01" required>
		</div>
		<div class="mb-3">
			<label for="image" class="form-label">Image</label>
			<input type="file" class="form-control" id="image" name="image" accept="image/*" required>
		</div>
		<button type="submit">Add Product</button>
	</form>
</div>
<!-- Bootstrap JS -->
<script src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap/5.1.3/js/bootstrap.min.js"></script>
				
				</body>
</html>
				
