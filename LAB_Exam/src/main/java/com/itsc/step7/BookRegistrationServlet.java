//TASK-7

package com.itsc.step7;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.itsc.OnlineBookstore.DBConnectionManager;

@Component
public class BookRegistrationServlet extends HttpServlet{
	
	@Autowired
	private DBConnectionManager dbManager = new DBConnectionManager();
	
	@Autowired
	public BookRegistrationServlet(DBConnectionManager dbManager) {
		
	}
	
	public BookRegistrationServlet() {}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException{
		String title = req.getParameter("title");
		String author = req.getParameter("author");
		String price = req.getParameter("price");
		
		try(Connection connection = dbManager.openConnection()){
			String query = "INSERT INTO Books (title, author, price) VALUES (?,?,?)";
			PreparedStatement statement = connection.prepareStatement(query);
			
			statement.setString(1, title);
			statement.setString(2, author);
			statement.setString(3, price);
			
			statement.executeUpdate();
			
			res.getWriter().println("<h1>Book added successfully</h1>");
			
			res.getWriter().println("<a href='index.html'>Home</a>");
		} catch (SQLException | ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	
}


