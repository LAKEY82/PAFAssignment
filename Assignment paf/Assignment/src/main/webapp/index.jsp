<%@page import="model.Item"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%
if (request.getParameter("itemCode") != null) 
{ 
 Item itemObj = new Item(); 
 String stsMsg = ""; 
//Insert--------------------------
if (request.getParameter("hidpowerIDSave") == "") 
 { 
 stsMsg = itemObj.insertItem(request.getParameter("period"), 
 request.getParameter("units"), 
 request.getParameter("unitPrice"), 
 request.getParameter("total")); 
 } 
else
	//Update----------------------
 { 
 stsMsg = itemObj.updateItem(request.getParameter("hidpowerIDSave"), 
 request.getParameter("period"), 
 request.getParameter("units"), 
 request.getParameter("unitPrice"), 
 request.getParameter("total")); 
 } 
 session.setAttribute("statusMsg", stsMsg); 
} 
//Delete-----------------------------
if (request.getParameter("hidItemIDDelete") != null) 
{ 
 Item itemObj = new Item(); 
 String stsMsg = 
 itemObj.deleteItem(request.getParameter("hidItemIDDelete")); 
 session.setAttribute("statusMsg", stsMsg); 
}

%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Power Consumption Management</title>
<link rel="stylesheet" href="Views/bootstrap.min.css">
<script src="Components/jquery-3.2.1.min.js"></script>
<script src="Components/items.js"></script>
</head>
<body> 
<div class="container"><div class="row"><div class="col-6"> 
<h1>Power Consumption </h1>
<form id="formItem" name="formItem">
 Item code: 
 <input id="period" name="period" type="text" 
 class="form-control form-control-sm">
 <br> Month name: 
 <input id="unitPrice" name="unitPrice" type="text" 
 class="form-control form-control-sm">
 <br> Item price: 
 <input id="units" name="units" type="text" 
 class="form-control form-control-sm">
 <br> Item description: 
 <input id="total" name="total" type="text" 
 class="form-control form-control-sm">
 <br>
 <input id="btnSave" name="btnSave" type="button" value="Save" 
 class="btn btn-primary">
 <input type="hidden" id="hidItemIDSave" 
 name="hidItemIDSave" value="">
</form>
<div id="alertSuccess" class="alert alert-success"></div>
<div id="alertError" class="alert alert-danger"></div>
<br>
<div id="divItemsGrid">
 <%
 Item itemObj = new Item(); 
 out.print(itemObj.readItems()); 
 %>
</div>
</div> </div> </div> 
</body>
</html>
