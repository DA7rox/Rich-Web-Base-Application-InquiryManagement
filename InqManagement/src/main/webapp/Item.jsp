<%@page import= "com.Item" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
  
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Feedback</title>
<link rel="stylesheet" href="View/bootstrap.min.css">
<script src="Components/jquery-3.2.1.min.js"></script>
<script src="Components/Item.js"></script>
</head>
<body>

<div class="container"><div class="row"><div class="col-6">

<h1>Inquery Management </h1>
<form id="formItem" name="formItem" method="post" action="Item.jsp">
 Id: 
<input id="id" name="id" type="text" 
 class="form-control form-control-sm">
<br> Name: 
<input id="name" name="name" type="text" 
 class="form-control form-control-sm">
<br> Email: 
<input id="email" name="email" type="text" 
 class="form-control form-control-sm">
<br> Message: 
<input id="message" name="message" type="text" 
 class="form-control form-control-sm">
<br>
<input id="btnSave" name="btnSave" type="button" value="Save" 
 class="btn btn-primary">
<input type="hidden" id="hidItemIDSave" name="hidItemIDSave" value="">
</form>

<div id="alertSuccess" class="alert alert-success"></div>
<div id="alertError" class="alert alert-danger"></div>

 
<br>
<div id="divItemsGrid">

<%
 Item itemObj = new Item(); 
 out.print(itemObj.readInq()); 
%>
</div>

</div></div></div>

</body>
</html>