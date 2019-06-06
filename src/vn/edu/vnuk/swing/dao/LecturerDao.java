package vn.edu.vnuk.swing.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import vn.edu.vnuk.swing.jdbc.ConnectionFactory;
import vn.edu.vnuk.swing.define.Define;
import vn.edu.vnuk.swing.model.Lecturer;
import vn.edu.vnuk.swing.model.Person;

public class LecturerDao {
	private Connection connection;

	public LecturerDao() {
		this.connection = new ConnectionFactory().getConnection();
	}
	
	@SuppressWarnings("finally")
	public long create(Lecturer lecturer) throws SQLException {
	
		long personId = new PersonDao().create(new Person(lecturer.getName(), lecturer.getType(), lecturer.getYearOfBirth()));
		
		if (connection.isClosed()) this.connection = new ConnectionFactory().getConnection();
		
		long idReturned = -1;
		
		String sqlQuery = "insert into Lecturers (Allowance, Department, Hometown, MinimumWage, Qualification, SalaryRatio, PeriodsInMonth, YearOfWork, PersonID) "
                + "values (?, ?, ?, ?, ?, ?, ?, ?, ?);";
		
		System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
		System.out.println(">  Insert lecturer started");
		
		PreparedStatement statement;
		
		try {
			statement = connection.prepareStatement(sqlQuery, Statement.RETURN_GENERATED_KEYS);

            //	Replacing "?" through values
            statement.setInt(1, lecturer.getAllowance());
            statement.setString(2, lecturer.getDepartment());
            statement.setString(3, lecturer.getHometown());
            statement.setFloat(4, lecturer.getMinimumWage());
            statement.setString(5, lecturer.getQualification());
            statement.setFloat(6, lecturer.getSalaryRatio());
            statement.setInt(7, lecturer.getPeriodsInMonth());
            statement.setInt(8, lecturer.getYearOfWork());
            statement.setLong(9, personId);
            
            // 	Executing statement
            statement.execute();
			
            ResultSet generatedKeys = statement.getGeneratedKeys();
			if (generatedKeys.next()) {
				idReturned = generatedKeys.getLong(1);
				System.out.println("   DATA successfully loaded in \'lecturers\'");
			} else {
				System.out.println("   Creating user failed, no ID obtained.");
			}
			
			statement.close();
		}
		
		catch (Exception e) {
	        e.printStackTrace();
		}
		
		finally {
			System.out.println("<  Insert lecturer ended");
			System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
			System.out.println("");
			connection.close();
			return idReturned;
		}
	}
	
	@SuppressWarnings("finally")
	public List<Lecturer> read(String keyword) throws SQLException {

		if (connection.isClosed()) this.connection = new ConnectionFactory().getConnection();
		
		List<Lecturer> lecturers = new ArrayList<Lecturer>();
		String sqlQuery = "select Persons.ID, "
				+         "       Persons.Name, "
				+         "       Persons.Type, "
				+         "       Persons.YearOfBirth, "
				+ 		  "       Lecturers.Allowance, "
				+ 		  "       Lecturers.Department, "
				+ 		  "       Lecturers.Hometown, "
				+ 		  "       Lecturers.MinimumWage, "
				+ 		  "       Lecturers.Qualification, "
				+ 		  "		  Lecturers.SalaryRatio, "
				+         "		  Lecturers.PeriodsInMonth, "
				+ 		  "		  Lecturers.YearOfWork, "
				+ 		  "		  Lecturers.PersonID "
				+ 		  "from Lecturers "
				+ 		  "inner join Persons "
				+         "on Persons.ID = Lecturers.PersonID "
				+ 		  "where name LIKE '%?%';";
	
		System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
		System.out.println(">  Read lecturers started");
		
		PreparedStatement statement;
		
		try {			
			statement = connection.prepareStatement(sqlQuery);
			
			statement.setString(1, keyword);
			
			ResultSet results = statement.executeQuery();
			
			while(results.next()) {
				lecturers.add(buildLecturer(results));
			}
			
			results.close();
			statement.close();
		}		
		catch (Exception e) {
	        e.printStackTrace();
		}		
		finally {
			System.out.println("<  Read lecturers ended");
			System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
			System.out.println("");
			connection.close();
			return lecturers;
		}
	}
	
	@SuppressWarnings("finally")
	public Lecturer read(Long id) throws SQLException {

		if (connection.isClosed()) this.connection = new ConnectionFactory().getConnection();
		
		Lecturer lecturer = new Lecturer();
		
		String sqlQuery = "select Persons.ID, "
				+         "       Persons.Name, "
				+         "       Persons.Type, "
				+         "       Persons.YearOfBirth, "
				+ 		  "       Lecturers.Allowance, "
				+ 		  "       Lecturers.Department, "
				+ 		  "       Lecturers.Hometown, "
				+ 		  "       Lecturers.Qualification, "
				+ 		  "		  Lecturers.SalaryRatio, "
				+         "		  Lecturers.PeriodsInMonth, "
				+ 		  "		  Lecturers.YearOfWork, "
				+ 		  "		  Lecturers.PersonID "
				+ 		  "from Lecturers "
				+ 		  "inner join Persons "
				+         "on Persons.ID = Lecturers.PersonID "
				+ 		  "where Lecturers.PersonID = ?;";
		
		PreparedStatement statement;
		
		try {
			statement = connection.prepareStatement(sqlQuery);
			
			statement.setLong(1, id);
			
			ResultSet results = statement.executeQuery();
			
			if (results.next()) {
				
				lecturer = buildLecturer(results);
			}
				
			results.close();
			statement.close();
		}		
		catch(Exception e) {
			e.printStackTrace();
		}		
		finally {
			connection.close();
			return lecturer;
		}
	}
	
	public void update(Long id, Lecturer lecturer) throws SQLException {
		
		new PersonDao().update(lecturer.getPersonId(), new Person(lecturer.getName(), lecturer.getType(), lecturer.getYearOfBirth()));
		
		if (connection.isClosed()) this.connection = new ConnectionFactory().getConnection();
		
		String sqlQuery = "update Lecturers "
						+ "set Lecturers.Allowance = ?, "
						+ "    Lecturers.Department = ?, "
						+ "    Lecturers.Hometown = ?, "
						+ "    Lecturers.Qualification = ?, "
						+ "    Lecturers.SalaryRatio = ?, "
						+ "    Lecturers.PeriodsInMonth = ?, "
						+ "    Lecturers.YearOfWork = ? "
						+ "where PersonID = ?";
		
		System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
		System.out.println(">  Update lecturer started");
		
		PreparedStatement statement;
		
		try {
			statement = connection.prepareStatement(sqlQuery);

            //	Replacing "?" through values
            statement.setInt(1, lecturer.getAllowance());
            statement.setString(2, lecturer.getDepartment());
            statement.setString(3, lecturer.getHometown());
            statement.setString(4, lecturer.getQualification());
            statement.setFloat(5, lecturer.getSalaryRatio());
            statement.setInt(6, lecturer.getPeriodsInMonth());
            statement.setInt(7, lecturer.getYearOfWork());
            statement.setLong(8, id);
            
            // 	Executing statement
			int rowsUpdated = statement.executeUpdate();
			if (rowsUpdated > 0) {
				System.out.println("   Update lecturer Successfully");	
			} else {
				System.out.println("   Id doesn't exist");
			}
			
			statement.close();
		}
		
		catch (Exception e) {
	        e.printStackTrace();
		}
		
		finally {
			System.out.println("<  Update lecturer ended");
			System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
			System.out.println("");
			connection.close();
		}
	}
	
	public void delete(Long id) throws SQLException {

		Lecturer lecturer = read(id);
		
		if (connection.isClosed()) this.connection = new ConnectionFactory().getConnection();
		
		String sqlQuery = "delete from Lecturers where PersonID = ?;";
		
		System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
		System.out.println(">  Delete lecturer started");
		
		PreparedStatement statement;
		
		try {
			
			statement = connection.prepareStatement(sqlQuery);
			
			statement.setLong(1, id);
			
			if(lecturer.getId() != 0) {
				statement.executeUpdate();
				System.out.println("   Delete staff Successfully");	
			} else { 
				System.out.println("   Error: ID NOT FOUND!");
			}
			
			statement.close();
			
			new PersonDao().delete(lecturer.getId());
		}
		
		catch(Exception e) {
			e.printStackTrace();
		}
		
		finally {
			System.out.println("<  Delete lecturer ended");
			System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
			System.out.println("");
			connection.close();
		}
		
	}
	
	private Lecturer buildLecturer(ResultSet results) throws SQLException {
		Lecturer lecturer = new Lecturer();
		
		lecturer.setId(results.getLong("ID"));
		lecturer.setName(results.getString("Name"));
		lecturer.setType(results.getInt("Type"));
		lecturer.setYearOfBirth(results.getInt("YearOfBirth"));
		
		lecturer.setAllowance(results.getInt("Allowance"));
		lecturer.setDepartment(results.getString("Department"));
		lecturer.setHometown(results.getString("Hometown"));
		lecturer.setMinimumWage(Define.DEFAULT_MINIMUM_WAGE);
		lecturer.setPersonId(results.getInt("PersonID"));
		lecturer.setQualification(results.getString("Qualification"));
		lecturer.setSalaryRatio(results.getFloat("SalaryRatio"));
		lecturer.setPeriodsInMonth(results.getInt("PeriodsInMonth"));
		lecturer.setYearOfBirth(results.getInt("YearOfWork"));
		
		return lecturer;
	}
}
