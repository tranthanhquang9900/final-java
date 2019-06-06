package vn.edu.vnuk.swing.sql;

import java.sql.Connection;
import java.sql.SQLException;

public class Sql1010CreateDatabase {
	private final Connection connection;
	private final String sqlQuery;
	
	public Sql1010CreateDatabase(Connection connection) {
		this.connection = connection;
		this.sqlQuery = "CREATE DATABASE IF NOT EXISTS databases_employee;";
	}
	
	public void run() throws SQLException {

		System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
		System.out.println(">  Sql1010CreateDatabase started");
		
		try {
	        connection.prepareStatement(sqlQuery).execute();
	        System.out.println("   DB \'databases_employee\' successfully created");
		
		}
		
		catch (Exception e) {
	        e.printStackTrace();
	        connection.close();
		}
		
		finally {
			System.out.println("<  Sql1010CreateDatabase ended");
			System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
			System.out.println("");
		}
			
	}
}