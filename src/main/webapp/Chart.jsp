<%@ page import = "cn.ShoppingCart.connection.DbCon" %>
<%@ page import = "cn.ShoppingCart.dao.ProductDao" %>
<%@page import="cn.ShoppingCart.model.*"%>
<%@page import="java.sql.*"%>

<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%
Connection connection=DriverManager.getConnection("jdbc:mysql://localhost:3306/ecommerce_cart","root","Hafsaougha@2022");
ProductDao p=new ProductDao(connection);
String chart=null;
 
 
 chart = p.getchart();



%>


<html>
  <head>
    <script type="text/javascript" src="https://www.gstatic.com/charts/loader.js"></script>
    <script type="text/javascript">
      google.charts.load('current', {'packages':['corechart']});
      google.charts.setOnLoadCallback(drawVisualization);

      function drawVisualization() {
        // Some raw data (not necessarily accurate)
        var data = <%=chart%>

        var options = {
          title : 'Monthly Coffee Production by Country',
          vAxis: {title: 'Cups'},
          hAxis: {title: 'Month'},
          seriesType: 'bars',
          series: {5: {type: 'line'}}
        };

        var chart = new google.visualization.ComboChart(document.getElementById('chart_div'));
        chart.draw(data, options);
      }
    </script>
  </head>
  <body>
    <div class="card p-3 text-right">
    <blockquote class="blockquote mb-0">
     <div id="chart_div" style="width: 700px; height: 280px;"></div>
     <h1>hello</h1>
    </blockquote>
  </div>
    
  </body>
</html>
