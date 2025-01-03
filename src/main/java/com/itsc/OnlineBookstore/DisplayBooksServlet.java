//TASK-5

package com.itsc.OnlineBookstore;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/displayBooks")
public class DisplayBooksServlet extends HttpServlet{
	
	private DBConnectionManager dbManager = new DBConnectionManager();
	
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws IOException {
		try(Connection connection = dbManager.openConnection();
				Statement statement = connection.createStatement();
				ResultSet resultSet = statement.executeQuery("select * from books")){
			PrintWriter pw = res.getWriter();
			pw.println("<table border='1'><tr><th>ID</th><th>Title</th><th>Author</th><th>Price</th></tr>");
			while(resultSet.next()) {
				pw.println("<tr><td>" + resultSet.getInt("id") +"</td>");
				pw.println("<td>" + resultSet.getString("title") + "</td>");
				pw.println("<td>" + resultSet.getString("author") + "</td>");
				pw.println("<td>" + resultSet.getInt("price")+"</td></tr>");
			}
			pw.println("</table>");
			pw.println("<a href='index.html'>Home</a>");
		} catch (SQLException | ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	

}

