
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Menu</title>
<link href="menuStyle.css" rel="stylesheet" type="text/css"/>
</head>
<body>
<%@ page import="java.sql.*" %>
<%@ page import="javax.sql.*" %>
<%
// Load the MySQL driver
Class.forName("com.mysql.jdbc.Driver");

// Connect to the database
String url = "jdbc:mysql://localhost:3306/mini";
String user = "root";
String password = "Rhucha2301$";
Connection conn = DriverManager.getConnection(url, user, password);

// Execute the select query
Statement stmt = conn.createStatement();
String query = "Select * from product,hotel_product where product.PID=hotel_product.PID and HID=105";
ResultSet rs = stmt.executeQuery(query);

// Display the results in a table

out.println("<table>");
out.println("<caption>MENU OF LCR</caption>");
out.println("<tr><th>Product ID</th><th>Product Name</th><th>Price</th><th>Category</th></tr>");
while (rs.next()) {
    int pid = rs.getInt("product.PID");
    String pname = rs.getString("product.PName");
    int price = rs.getInt("product.Price");
    String cat = rs.getString("product.Category");
    out.println("<tr><td>" + pid + "</td><td>" + pname + "</td><td>" + price + "</td><td>" + cat+ "</td></tr>");
}
out.println("</table>");

// Close the database connection
rs.close();
stmt.close();
conn.close();
%>
</body>
</html>
