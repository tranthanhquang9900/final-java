package vn.edu.vnuk.swing.sql;

import java.sql.Connection;
import java.sql.SQLException;

public class Sql2030CreateLecturers {
	private final Connection connection;
	private final String sqlQuery;
	
	public Sql2030CreateLecturers(Connection connection) {
		this.connection = connection;
		
		this.sqlQuery = "CREATE TABLE IF NOT EXISTS Lecturers ("
				+ 	"ID INT NOT NULL AUTO_INCREMENT PRIMARY KEY, "
				+ 	"PersonID INT NOT NULL,"
				+ 	"Hometown VARCHAR(255) NOT NULL,"
				+ 	"Department VARCHAR(255) NOT NULL,"
				+	"Qualification VARCHAR(255) NOT NULL,"
				+	"SalaryRatio FLOAT NOT NULL,"
				+	"Allowance INT NOT NULL,"
				+	"PeriodsInMonth INT NOT NULL,"
				+	"YearOfWork INT NOT NULL,"
				+	"MinimumWage FLOAT NOT NULL,"
				+	"FOREIGN KEY (PersonID) REFERENCES Persons(ID)"
				+ ");"
			;
	}
	
	public void run() throws SQLException {

		System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
		System.out.println(">  Sql2030CreateLecturers started");
		
		try {
	        connection.prepareStatement(sqlQuery).execute();
	        System.out.println("   TABLE \'Lecturers\' successfully created");
		
		}
		
		catch (Exception e) {
	        e.printStackTrace();
	        connection.close();
		}
		
		finally {
			System.out.println("<  Sql2030CreateLecturers ended");
			System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
			System.out.println("");
		}
			
	}
}