//TASK-10

package com.itsc.OnlineBookstore;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

// Annotation-based servlet mapping
@WebServlet("/searchBook")
public class SearchBooksServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Get the search query from the request
        String searchQuery = request.getParameter("title");
        response.setContentType("text/html");

        try (PrintWriter out = response.getWriter()) {
            if (searchQuery == null || searchQuery.trim().isEmpty()) {
                out.println("<h3>Error: Please provide a search title.</h3>");
                return;
            }

            // Query the database
            List<String[]> books = searchBooks(searchQuery);

            // Display results in HTML
            out.println("<h1>Search Results</h1>");
            if (books.isEmpty()) {
                out.println("<p>No Books found matching the title.</p>");
                out.println("<a href='index.html'>Home</a>");
                
            } else {
                out.println("<table border='1'>");
                out.println("<tr><th>ID</th><th>Title</th><th>Author</th><th>Price</th></tr>");
                for (String[] book : books) {
                    out.printf("<tr><td>%s</td><td>%s</td><td>%s</td><td>%s</td></tr>",
                               book[0], book[1], book[2], book[3]);
                }
                out.println("</table>");
                out.println("<a href='index.html'>Home</a>");
            }
        } catch (Exception e) {
            e.printStackTrace();
            response.getWriter().println("Error occurred while processing your request.");
        }
    }

    private List<String[]> searchBooks(String searchQuery) throws Exception {
        List<String[]> books = new ArrayList<>();
        String query = "SELECT * FROM Books WHERE title LIKE ?";
        try (Connection connection = new DBConnectionManager().openConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, "%" + searchQuery + "%");

            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    books.add(new String[]{
                            String.valueOf(resultSet.getInt("id")),
                            resultSet.getString("title"),
                            resultSet.getString("author"),
                            String.valueOf(resultSet.getInt("price"))
                    });
                }
            }
        }
        return books;
    }
}
