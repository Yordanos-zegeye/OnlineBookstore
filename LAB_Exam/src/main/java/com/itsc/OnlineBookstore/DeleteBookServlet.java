//TASK-6

package com.itsc.OnlineBookstore;

import java.sql.Connection;
import java.sql.PreparedStatement;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/deleteBook")
public class DeleteBookServlet extends HttpServlet{
	
	private DBConnectionManager dbManager = new DBConnectionManager();
	
	@Override
	protected void doPost(HttpServletRequest req,HttpServletResponse res) {
		
		String id = req.getParameter("id");
		res.setContentType("text/html");
		
		try {
			Connection con = dbManager.openConnection();
			String query = "DELETE FROM Books WHERE ID=?";
			PreparedStatement statement = con.prepareStatement(query);
			
			statement.setInt(1, Integer.parseInt(id));
			
			int rows = statement.executeUpdate();
			res.getWriter().println( rows>0 ? "<h1>Book deleted successfully</h1>" : "<h1>Book not found!</h1>");
			
			res.getWriter().println("<a href='index.html'>Home</a>");
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
}
