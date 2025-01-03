//TASK-3

package com.itsc.OnlineBookstore;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import org.springframework.stereotype.Component;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Component
public class DBConnectionManager{
	
	String url = "jdbc:mysql://localhost:3306/BookstoreDB";
	String username = "root";
	String password = "2405";
	
	public Connection openConnection() throws SQLException, ClassNotFoundException{
		Class.forName("com.mysql.cj.jdbc.Driver");

		Connection conn = DriverManager.getConnection(url , username,password);
		return conn;
	}
	
    public void closeConnection(Connection connection) throws SQLException {
        if (connection != null && !connection.isClosed()) {
            connection.close();
        }
    }

	
}