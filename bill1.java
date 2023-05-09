

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class bill1
 */
@WebServlet("/bill1")
public class bill1 extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int oid=Integer.parseInt(request.getParameter("Oid"));
		
		HttpSession session=request.getSession();
		RequestDispatcher dispatcher=null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			 Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/mini","root","Rhucha2301$");
			 PreparedStatement pst=con.prepareStatement("SELECT order_product.OID, product.PName, order_product.Qty, product.Price, (order_product.Qty * product.Price) AS total_price FROM order_product JOIN product ON order_product.PID = product.PID WHERE order_product.OID =?");
			 pst.setInt(1, oid);
			 
			 
			 ResultSet rs=pst.executeQuery();
			 
			 PrintWriter out = response.getWriter();
		        out.println("<html>");
		        out.println("<head>");
		        out.println("<link href=\"menuStyle.css\" rel=\"stylesheet\" type=\"text/css\"/>");
		        out.println("</head>");
		        out.println("<body>");
		        out.println("<table>");
		        out.println("<tr><td>" + "Order ID" + "</td><td>"+"Product Name" + "</td><td>" +"Quantity" +   "</td><td>" + "Price" +  "</td><td>" + "Total Price"+ "</td></tr>");
		        while (rs.next()) {
		            out.println("<tr>");
		            out.println("<td>" + rs.getInt("order_product.OID") + "</td>");
		            out.println("<td>" + rs.getString("product.PName") + "</td>");
		            out.println("<td>" + rs.getInt("order_product.Qty") + "</td>");
		            out.println("<td>" + rs.getString("product.Price") + "</td>");
		            out.println("<td>" + rs.getInt("total_price") + "</td>");
		            out.println("</tr>");
		        }
		        out.println("</table>");
		        out.println("</body>");
		        out.println("</html>");
		        
		        // Clean up resources
		        rs.close();
		        pst.close();
		        con.close();
		
	}catch(Exception e) {
		e.printStackTrace();
	}
	}

	}


