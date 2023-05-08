

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class order5
 */
@WebServlet("/order5")
public class order5 extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int oid=Integer.parseInt(request.getParameter("Oid"));
		int pid=Integer.parseInt(request.getParameter("Pid"));
		int qty=Integer.parseInt(request.getParameter("QTY"));
		RequestDispatcher dispatcher=null;
		Connection con=null;
		
		
		try {
			
			Class.forName("com.mysql.jdbc.Driver");
			 con=DriverManager.getConnection("jdbc:mysql://localhost:3306/mini","root","Rhucha2301$");
			PreparedStatement pst=con.prepareStatement("Insert into order_product (OID,PID,Qty) values (?,?,?)");
			pst.setInt(1, oid);
			pst.setInt(2, pid);
			pst.setInt(3, qty);
			
			int rowCount=pst.executeUpdate();
			dispatcher=request.getRequestDispatcher("Menu5.jsp");
			if(rowCount>0) {
				request.setAttribute("status", "success");
			}
			else {
				request.setAttribute("status", "failed");
			}
			
			dispatcher.forward(request, response);
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		
	}

}
