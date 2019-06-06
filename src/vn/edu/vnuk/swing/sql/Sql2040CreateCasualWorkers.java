package vn.edu.vnuk.swing.sql;

import java.sql.Connection;
import java.sql.SQLException;

public class Sql2040CreateCasualWorkers {
	private final Connection connection;
	private final String sqlQuery;
	
	public Sql2040CreateCasualWorkers(Connection connection) {
		this.connection = connection;
		
		this.sqlQuery = "CREATE TABLE IF NOT EXISTS CasualWorkers ("
				+ 	"ID INT NOT NULL AUTO_INCREMENT PRIMARY KEY, "
				+ 	"PersonID INT NOT NULL,"
				+ 	"WorkDay INT NOT NULL,"
				+	"EarningPerDay FLOAT NOT NULL,"
				+	"FOREIGN KEY (PersonID) REFERENCES Persons(ID)"
				+ ");"
			;
	}
	
	public void run() throws SQLException {

		System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
		System.out.println(">  Sql2040CreateCasualWorkers started");
		
		try {
	        connection.prepareStatement(sqlQuery).execute();
	        System.out.println("   TABLE \'CasualWorkers\' successfully created");
		
		}
		
		catch (Exception e) {
	        e.printStackTrace();
	        connection.close();
		}
		
		finally {
			System.out.println("<  Sql2040CreateCasualWorkers ended");
			System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
			System.out.println("");
		}
			
	}
}