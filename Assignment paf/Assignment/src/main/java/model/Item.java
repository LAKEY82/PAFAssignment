  package model; 
import java.sql.*; 


public class Item 
{ 
private Connection connect() 
 { 
 Connection con = null; 
 try
 { 
 Class.forName("com.mysql.jdbc.Driver"); 
 con = 
 DriverManager.getConnection( 
 "jdbc:mysql://127.0.0.1:3306/test", "root", ""); 
 } 
 catch (Exception e) 
 { 
 e.printStackTrace(); 
 } 
 return con; 
 } 





public String readItems() 
{ 
 String output = ""; 
try
 { 
 Connection con = connect(); 
 if (con == null) 
 { 
 return "Error while connecting to the database for reading."; 
 } 
 // Prepare the html table to be displayed
 output = "<table border='1'><tr><th>Item Code</th>" 
		 +"<th>Item Name</th><th>Item Price</th>"
		 +"<th>Item Description</th>" 
 + "<th>Update</th><th>Remove</th></tr>"; 
 String query = "select * from items"; 
 Statement stmt = con.createStatement(); 
 ResultSet rs = stmt.executeQuery(query); 
 // iterate through the rows in the result set
 while (rs.next()) 
 { 
 String powerID = Integer.toString(rs.getInt("powerID")); 
 String period = rs.getString("period"); 
 String units = rs.getString("units"); 
 String unitPrice = Double.toString(rs.getDouble("unitPrice")); 
 String total = rs.getString("total"); 
 
 // Add into the html table
 output += "<tr><td>" + period + "</td>"; 
 output += "<td>" + units + "</td>"; 
 output += "<td>" + unitPrice + "</td>"; 
 output += "<td>" + total + "</td>"; 
// buttons
output += "<td><input name='btnUpdate' type='button' value='Update' "
+ "class='btnUpdate btn btn-secondary' data-itemid='" + powerID + "'></td>"
+ "<td><input name='btnRemove' type='button' value='Remove' "
+ "class='btnRemove btn btn-danger' data-itemid='" + powerID + "'></td></tr>"; 
 } 
 con.close(); 
 // Complete the html table
 output += "</table>"; 
 } 
catch (Exception e) 
 { 
 output = "Error while reading the items."; 
 System.err.println(e.getMessage()); 
 } 
return output; 
}






public String insertItem(String month, String amount, 
		 String price, String total) 
		 { 
		 String output = ""; 
		 try
		 { 
		 Connection con = connect(); 
		 if (con == null) 
		 { 
		 return "Error while connecting to the database for inserting."; 
		 } 
		 // create a prepared statement
		 String query = " insert into items (`powerID`,`period`,`units`,`unitPrice`,`total`)"
		 + " values (?, ?, ?, ?, ?)"; 
		 PreparedStatement preparedStmt = con.prepareStatement(query); 
		 // binding values
		 preparedStmt.setInt(1, 0); 
		 preparedStmt.setString(2, month); 
		 preparedStmt.setString(3, amount); 
		 preparedStmt.setDouble(4, Double.parseDouble(price)); 
		 preparedStmt.setString(5, total); 
		 // execute the statement
		 preparedStmt.execute(); 
		 con.close(); 
		 String newItems = readItems(); 
		 output = "{\"status\":\"success\", \"data\": \"" + 
		 newItems + "\"}"; 
		 } 
		 catch (Exception e) 
		 { 
		 output = "{\"status\":\"error\", \"data\": \"Error while inserting the item.\"}"; 
		 System.err.println(e.getMessage()); 
		 } 
		 return output; 
		 } 
		





public String updateItem(String ID, String month, String amount, 
		 String price, String total) 
		 { 
		 String output = ""; 
		 try
		 { 
		 Connection con = connect(); 
		 if (con == null) 
		 { 
		 return "Error while connecting to the database for updating."; 
		 } 
		 // create a prepared statement
		 String query = "UPDATE items SET period=?,units=?,unitPrice=?,total=? WHERE itemID=?"; 
		 PreparedStatement preparedStmt = con.prepareStatement(query); 
		 // binding values
		 preparedStmt.setString(1, month); 
		 preparedStmt.setString(2, amount); 
		 preparedStmt.setDouble(3, Double.parseDouble(price)); 
		 preparedStmt.setString(4, total); 
		 preparedStmt.setInt(5, Integer.parseInt(ID)); 
		 
		 // execute the statement
		 preparedStmt.execute(); 
		 con.close(); 
		 String newItems = readItems(); 
		 output = "{\"status\":\"success\", \"data\": \"" + 
		 newItems + "\"}"; 
		 } 
		 catch (Exception e) 
		 { 
		 output = "{\"status\":\"error\", \"data\": \"Error while updating the item.\"}"; 
		 System.err.println(e.getMessage()); 
		 } 
		 return output; 
		 } 
		
		
		
		
		
		public String deleteItem(String powerID) 
		 { 
		 String output = ""; 
		 try
		 { 
		 Connection con = connect(); 
		 if (con == null) 
		 { 
		 return "Error while connecting to the database for deleting."; 
		 } 
		 // create a prepared statement
		 String query = "delete from items where itemID=?"; 
		 PreparedStatement preparedStmt = con.prepareStatement(query); 
		 // binding values
		 preparedStmt.setInt(1, Integer.parseInt(powerID)); 
		 // execute the statement
		 preparedStmt.execute(); 
		 con.close(); 
		 String newItems = readItems(); 
		 output = "{\"status\":\"success\", \"data\": \"" + 
		 newItems + "\"}"; 
		 } 
		 catch (Exception e) 
		 { 
		 output = "{\"status\":\"error\", \"data\": \"Error while deleting the item.\"}"; 
		 System.err.println(e.getMessage()); 
		 } 
		 return output; 
		 } 
		}



