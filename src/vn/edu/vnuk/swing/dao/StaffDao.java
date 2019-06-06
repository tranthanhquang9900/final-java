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
import vn.edu.vnuk.swing.model.Person;
import vn.edu.vnuk.swing.model.Staff;

public class StaffDao {
	private Connection connection;

	public StaffDao() {
		this.connection = new ConnectionFactory().getConnection();
	}
	
	@SuppressWarnings("finally")
	public long create(Staff staff) throws SQLException {
	
		long personId = new PersonDao().create(new Person(staff.getName(), staff.getType(), staff.getYearOfBirth()));
		
		if (connection.isClosed()) this.connection = new ConnectionFactory().getConnection();
		
		long idReturned = -1;
		
		String sqlQuery = "insert into Staffs (Allowance, Department, Hometown, MinimumWage, Position, SalaryRatio, WorkDay, YearOfWork, PersonID) "
                + "values (?, ?, ?, ?, ?, ?, ?, ?, ?);";
		
		System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
		System.out.println(">  Insert Staff started");
		
		PreparedStatement statement;
		
		try {
			statement = connection.prepareStatement(sqlQuery, Statement.RETURN_GENERATED_KEYS);

            //	Replacing "?" through values
            statement.setInt(1, staff.getAllowance());
            statement.setString(2, staff.getDepartment());
            statement.setString(3, staff.getHometown());
            statement.setFloat(4, staff.getMinimumWage());
            statement.setString(5, staff.getPosition());
            statement.setFloat(6, staff.getSalaryRatio());
            statement.setInt(7, staff.getWorkDay());
            statement.setInt(8, staff.getYearOfWork());
            statement.setLong(9, personId);
            
            // 	Executing statement
            statement.execute();
			
            ResultSet generatedKeys = statement.getGeneratedKeys();
			if (generatedKeys.next()) {
				idReturned = generatedKeys.getLong(1);
				System.out.println("   DATA successfully loaded in \'staffs\'");
			} else {
				System.out.println("   Creating user failed, no ID obtained.");
			}
			
			statement.close();
		}
		
		catch (Exception e) {
	        e.printStackTrace();
		}
		
		finally {
			System.out.println("<  Insert staff ended");
			System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
			System.out.println("");
			connection.close();
			return idReturned;
		}
	}
	
	@SuppressWarnings("finally")
	public List<Staff> read(String keyword) throws SQLException {

		if (connection.isClosed()) this.connection = new ConnectionFactory().getConnection();
		
		List<Staff> staffs = new ArrayList<Staff>();
		String sqlQuery = "select Persons.ID, "
				+         "       Persons.Name, "
				+         "       Persons.Type, "
				+         "       Persons.YearOfBirth, "
				+ 		  "       Staffs.Allowance, "
				+ 		  "       Staffs.Department, "
				+ 		  "       Staffs.Hometown, "
				+ 		  "       Staffs.MinimumWage, "
				+ 		  "       Staffs.Position, "
				+ 		  "		  Staffs.SalaryRatio, "
				+         "		  Staffs.WorkDay, "
				+ 		  "		  Staffs.YearOfWork, "
				+ 		  "		  Staffs.PersonID "
				+ 		  "from Staffs "
				+ 		  "inner join Persons "
				+         "on Persons.ID = Staffs.PersonID "
				+ 		  "where name LIKE '%?%';";
	
		System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
		System.out.println(">  Read staffs started");
		
		PreparedStatement statement;
		
		try {			
			statement = connection.prepareStatement(sqlQuery);
			
			statement.setString(1, keyword);
			
			ResultSet results = statement.executeQuery();
			
			while(results.next()) {
				staffs.add(buildStaff(results));
			}
			
			results.close();
			statement.close();
		}		
		catch (Exception e) {
	        e.printStackTrace();
		}		
		finally {
			System.out.println("<  Read staffs ended");
			System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
			System.out.println("");
			connection.close();
			return staffs;
		}
	}
	
	@SuppressWarnings("finally")
	public Staff read(Long id) throws SQLException {

		if (connection.isClosed()) this.connection = new ConnectionFactory().getConnection();
		
		Staff staff = new Staff();
		
		String sqlQuery = "select Persons.ID, "
				+         "       Persons.Name, "
				+         "       Persons.Type, "
				+         "       Persons.YearOfBirth, "
				+ 		  "       Staffs.Allowance, "
				+ 		  "       Staffs.Department, "
				+ 		  "       Staffs.Hometown, "
				+ 		  "       Staffs.MinimumWage, "
				+ 		  "       Staffs.Position, "
				+ 		  "		  Staffs.SalaryRatio, "
				+         "		  Staffs.WorkDay, "
				+ 		  "		  Staffs.YearOfWork, "
				+ 		  "		  Staffs.PersonID "
				+ 		  "from Staffs "
				+ 		  "inner join Persons "
				+         "on Persons.ID = Staffs.PersonID "
				+ 		  "where Staffs.PersonID = ?;";
		
		PreparedStatement statement;
		
		try {
			statement = connection.prepareStatement(sqlQuery);
			
			statement.setLong(1, id);
			
			ResultSet results = statement.executeQuery();
			
			if (results.next()) {
				
				staff = buildStaff(results);
			}
				
			results.close();
			statement.close();
		}		
		catch(Exception e) {
			e.printStackTrace();
		}		
		finally {
			connection.close();
			return staff;
		}
	}
	
	public void update(Long id, Staff staff) throws SQLException {
		
		new PersonDao().update(staff.getPersonId(), new Person(staff.getName(), staff.getType(), staff.getYearOfBirth()));
		
		if (connection.isClosed()) this.connection = new ConnectionFactory().getConnection();
		
		String sqlQuery = "update Staffs "
						+ "set Staffs.Allowance = ?, "
						+ "    Staffs.Department = ?, "
						+ "    Staffs.Hometown = ?, "
						+ "    Staffs.MinimumWage = ?, "
						+ "    Staffs.Position = ?, "
						+ "    Staffs.SalaryRatio = ?, "
						+ "    Staffs.WorkDay = ?, "
						+ "    Staffs.YearOfWork = ? "
						+ "where PersonID = ?";
		
		System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
		System.out.println(">  Update staff started");
		
		PreparedStatement statement;
		
		try {
			statement = connection.prepareStatement(sqlQuery);

            //	Replacing "?" through values
            statement.setInt(1, staff.getAllowance());
            statement.setString(2, staff.getDepartment());
            statement.setString(3, staff.getHometown());
            statement.setFloat(4, staff.getMinimumWage());
            statement.setString(5, staff.getPosition());
            statement.setFloat(6, staff.getSalaryRatio());
            statement.setInt(7, staff.getWorkDay());
            statement.setInt(8, staff.getYearOfBirth());
            statement.setLong(9, id);
            
            // 	Executing statement
			int rowsUpdated = statement.executeUpdate();
			if (rowsUpdated > 0) {
				System.out.println("   Update staff Successfully");	
			} else {
				System.out.println("   Id doesn't exist");
			}
			
			statement.close();
		}
		
		catch (Exception e) {
	        e.printStackTrace();
		}
		
		finally {
			System.out.println("<  Update staff ended");
			System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
			System.out.println("");
			connection.close();
		}
	}
	
	public void delete(Long id) throws SQLException {

		Staff staff = read(id);
		
		if (connection.isClosed()) this.connection = new ConnectionFactory().getConnection();
		
		String sqlQuery = "delete from Staffs where PersonID = ?;";
		
		System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
		System.out.println(">  Delete staff started");
		
		PreparedStatement statement;
		
		try {
			
			statement = connection.prepareStatement(sqlQuery);
			
			statement.setLong(1, id);
			
			if(staff.getId() != 0) {
				statement.executeUpdate();
				System.out.println("   Delete staff Successfully");	
			} else { 
				System.out.println("   Error: ID NOT FOUND!");
			}
			
			statement.close();
			
			new PersonDao().delete(staff.getId());
		}
		
		catch(Exception e) {
			e.printStackTrace();
		}
		
		finally {
			System.out.println("<  Delete staff ended");
			System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
			System.out.println("");
			connection.close();
		}
		
	}
	
	private Staff buildStaff(ResultSet results) throws SQLException {
		Staff staff = new Staff();
		
		staff.setId(results.getLong("ID"));
		staff.setName(results.getString("Name"));
		staff.setType(results.getInt("Type"));
		staff.setYearOfBirth(results.getInt("YearOfBirth"));
		
		staff.setAllowance(results.getInt("Allowance"));
		staff.setDepartment(results.getString("Department"));
		staff.setHometown(results.getString("Hometown"));
		staff.setMinimumWage(Define.DEFAULT_MINIMUM_WAGE);
		staff.setPersonId(results.getInt("PersonID"));
		staff.setPosition(results.getString("Position"));
		staff.setSalaryRatio(results.getFloat("SalaryRatio"));
		staff.setWorkDay(results.getInt("WorkDay"));
		staff.setYearOfBirth(results.getInt("YearOfWork"));
		
		return staff;
	}
}
