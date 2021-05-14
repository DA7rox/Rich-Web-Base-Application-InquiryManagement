package com;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class Item {
	public Connection connect()
	{ 
	 Connection con = null; 
	 
	 try 
	 { 
	 Class.forName("com.mysql.cj.jdbc.Driver"); 
	 con= DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/feedbackproject", 
	 "root", ""); 
	 //For testing
	 System.out.print("Successfully connected"); 
	 } 
	 catch(Exception e) 
	 { 
	 e.printStackTrace(); 
	 } 
	 
	 return con; 
		}



	public String insertInq( String name, String email, String message) {
		
		 String output = "";
		 
		 try {
		
		Connection con = connect();
		if (con == null) 
		{ 
		return "Error while connecting to the database"; 
		}
		
		// create a prepared statement
		String query = "insert into  feedbackpro  (name, email, message)"
				 + " values (?, ?, ?)"; 
		PreparedStatement preparedStmt = con.prepareStatement(query); 
		// binding values 
		
		preparedStmt.setString(1, name); 
		preparedStmt.setString(2, email); 
		preparedStmt.setString(3, message);
		
		
		 
		 preparedStmt.execute(); 
		 con.close(); 
		 
		 String newInq = readInq();
		 output =  "{\"status\":\"success\", \"data\": \"" + 
				 newInq + "\"}"; 
		 } 

		catch (Exception e) 
		 { 
			output = "{\"status\":\"error\", \"data\": \"Error while inserting the item.\"}";  
		 System.err.println(e.getMessage()); 
		 } 
		return output; 
		}


	public String readInq()
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
			 output = "<table border='1'><tr><th>Name</th>" 
			 +"<th>Email</th><th>Message</th>" 
			 + "<th>Update</th><th>Remove</th></tr>"; 
			 String query = "select * from  feedbackpro "; 
			 java.sql.Statement stmt = con.createStatement(); 
			 ResultSet rs = stmt.executeQuery(query); 
			 // iterate through the rows in the result set
			 while (rs.next()) 
			 { 
			 String id = Integer.toString(rs.getInt("id"));  
			 String name = rs.getString("name"); 
			 String email = rs.getString("email");
			 String message = rs.getString("message"); 
			// String id = rs.getString("id");
			 // Add a row into the html table
			 output += "<tr><td><input id='hidItemIDUpdate' name='hidItemIDUpdate' type='hidden' value='" + id + "'>"
					 + name + "</td>";
			 output += "<td>" + email + "</td>"; 
			 output += "<td>" + message + "</td>"; 
			 
			 
			 // buttons
			 output += "<td><input name='btnUpdate' " 
			 + " type='button' value='Update' class =' btnUpdate btn btn-secondary'data-itemid='" + id + "'></td>"
			 + "<td><form method='post' action='Item.jsp'>"
			 + "<input name='btnRemove' " 
			 + " type='button' value='Remove' class='btnRemove btn btn-danger' data-itemid='" + id + "'>"
			 + "<input name='hidItemIDDelete' type='hidden' " 
			 + " value='" + id + "'>" + "</form></td></tr>"; 
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

	public String deleteInq(String id)
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
	 String query = "delete from  feedbackpro  where  id=?"; 
	 PreparedStatement preparedStmt = con.prepareStatement(query); 
	 // binding values
	 preparedStmt.setInt(1, Integer.parseInt(id)); 
	 
	 // execute the statement
	 preparedStmt.execute(); 
	 con.close(); 
	 String newInq = readInq();
	 output =  "{\"status\":\"success\", \"data\": \"" + 
			 newInq + "\"}"; 
	 } 

	catch (Exception e) 
	 { 
		output = "{\"status\":\"error\", \"data\": \"Error while deleting the item.\"}";  
	 System.err.println(e.getMessage()); 
	 } 
	return output; 
		}

	public String updateInq(String id, String name, String email, String message)
	//4
	{
	String output = "";
	try {
	Connection conn = connect();
	if (conn == null) {
	return "Error while connecting to the database for updating.";
	}

	// create a prepared statement
	String query = "UPDATE  feedbackpro  SET name=?,email=?,message=? WHERE id=?";
	PreparedStatement preparedStmt = conn.prepareStatement(query);
	//binding values
	
	preparedStmt.setString(1, name);
	preparedStmt.setString(2, email);
	preparedStmt.setString(3,message);
	//execute the statement
	preparedStmt.execute();
	conn.close();
	String newInq = readInq();
	 output =  "{\"status\":\"success\", \"data\": \"" + 
			 newInq + "\"}"; 
	 } 

	catch (Exception e) 
	 { 
		output = "{\"status\":\"error\", \"data\": \"Error while Updating the item.\"}";  
	
	System.err.println(e.getMessage());
	}
	return output;
	}


}
