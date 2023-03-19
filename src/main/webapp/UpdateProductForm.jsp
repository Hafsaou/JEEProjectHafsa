<%@page import="cn.ShoppingCart.dao.ProductDao"%>
<%@page import="cn.ShoppingCart.connection.DbCon"%>
<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import ="java.util.*"%>
<%@page import="cn.ShoppingCart.model.*"%>
<%@page import="cn.ShoppingCart.*"%>

<%Product product=(Product) request.getAttribute("product");
  ProductDao pd= new ProductDao(DbCon.getConnection());
  List<String>  categories=pd.getAllCategories();
%>

 
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Update Product</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css"
          integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm"
          crossorigin="anonymous">
</head>
<body>
               
<div class="container">
    <h1 class="mt-4 mb-4">Update Product</h1>
    <form method="post" action="UpdateProduct"  >
        <div class="form-group">
            <label for="id">ID:</label>
            <input type="text" class="form-control" id="id" name="id2" value="${product.getId()}" readonly>
        </div>
        <div class="form-group">
            <label for="name">Name:</label>
            <input type="text" class="form-control" id="name" name="name" value="${product.getName()}" required>
        </div>
        <div class="form-group">
        <label for="category">Select a category:</label>
        <select class="form-control" id="category" name="category">
         <%    
  if(!categories.isEmpty()){
       for(String category : categories) {
		    if(category.equals(product.getCategory())){ 
		 %>
		  <option value=" ${product.getCategory()}" selected>${ product.getCategory()} </option>
		   <% 
		    }else {		    	
		   %>
		     <option value="<%= category%>"><%= category%></option>
 <% 
		    }
       }      
  }
  
  else 
  %>
  <option value="none">none</option>
    </select>
     </div>
        <div class="form-group">
            <label for="price">Price:</label>
            <input type="number" step="0.01" class="form-control" id="price" name="price" value="${product.getPrice()}" required>
        </div>
        
        <div class="form-group">
		    <label for="image">Image:</label>
		    <img src="product-image/${product.getImage()}" class="img-thumbnail" style="max-width: 200px;">
		    <input type="file" class="form-control-file" id="image" name="image">
        </div>
        
       <button type="submit" class="btn btn-primary">Update</button>
    </form>
   </div>
<script src="https://code.jquery.com/jquery-3.2.1.slim.min.js"
        integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN"
        crossorigin="anonymous"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"
        integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl"
        crossorigin="anonymous"></script>
</body>
</html>
 