package vn.edu.vnuk.swing.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import vn.edu.vnuk.swing.jdbc.ConnectionFactory;
import vn.edu.vnuk.swing.model.CasualWorker;
import vn.edu.vnuk.swing.model.Person;

public class CasualWorkerDao {
	private Connection connection;

	public CasualWorkerDao() {
		this.connection = new ConnectionFactory().getConnection();
	}
	
	@SuppressWarnings("finally")
	public long create(CasualWorker casualWorker) throws SQLException {
	
		long personId = new PersonDao().create(new Person(casualWorker.getName(), casualWorker.getType(), casualWorker.getYearOfBirth()));
		
		if (connection.isClosed()) this.connection = new ConnectionFactory().getConnection();
		
		long idReturned = -1;
		
		String sqlQuery = "insert into CasualWorkers (EarningPerDay, WorkDay, PersonID) "
                + "values (?, ?, ?);";
		
		System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
		System.out.println(">  Insert casualWorker started");
		
		PreparedStatement statement;
		
		try {
			statement = connection.prepareStatement(sqlQuery, Statement.RETURN_GENERATED_KEYS);

            //	Replacing "?" through values
            statement.setFloat(1, casualWorker.getEarningPerDay());
            statement.setInt(2, casualWorker.getWorkDay());
            statement.setLong(3, personId);
            
            // 	Executing statement
            statement.execute();
			
            ResultSet generatedKeys = statement.getGeneratedKeys();
			if (generatedKeys.next()) {
				idReturned = generatedKeys.getLong(1);
				System.out.println("   DATA successfully loaded in \'casualWorkers\'");
			} else {
				System.out.println("   Creating user failed, no ID obtained.");
			}
			
			statement.close();
		}
		
		catch (Exception e) {
	        e.printStackTrace();
		}
		
		finally {
			System.out.println("<  Insert casualWorker ended");
			System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
			System.out.println("");
			connection.close();
			return idReturned;
		}
	}
	
	@SuppressWarnings("finally")
	public List<CasualWorker> read(String keyword) throws SQLException {

		if (connection.isClosed()) this.connection = new ConnectionFactory().getConnection();
		
		List<CasualWorker> casualWorkers = new ArrayList<CasualWorker>();
		String sqlQuery = "select Persons.ID, "
				+         "       Persons.Name, "
				+         "       Persons.Type, "
				+         "       Persons.YearOfBirth, "
				+ 		  "       CasualWorkers.EarningPerDay, "
				+         "		  CasualWorkers.WorkDay, "
				+ 		  "		  CasualWorkers.PersonID "
				+ 		  "from CasualWorkers "
				+ 		  "inner join Persons "
				+         "on Persons.ID = CasualWorkers.PersonID "
				+ 		  "where name LIKE '%?%';";
	
		System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
		System.out.println(">  Read casualWorkers started");
		
		PreparedStatement statement;
		
		try {			
			statement = connection.prepareStatement(sqlQuery);
			
			statement.setString(1, keyword);
			
			ResultSet results = statement.executeQuery();
			
			while(results.next()) {
				casualWorkers.add(buildCasualWorker(results));
			}
			
			results.close();
			statement.close();
		}		
		catch (Exception e) {
	        e.printStackTrace();
		}		
		finally {
			System.out.println("<  Read casualWorkers ended");
			System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
			System.out.println("");
			connection.close();
			return casualWorkers;
		}
	}
	
	@SuppressWarnings("finally")
	public CasualWorker read(Long id) throws SQLException {

		if (connection.isClosed()) this.connection = new ConnectionFactory().getConnection();
		
		CasualWorker casualWorker = new CasualWorker();
		
		String sqlQuery = "select Persons.ID, "
				+         "       Persons.Name, "
				+         "       Persons.Type, "
				+         "       Persons.YearOfBirth, "
				+ 		  "       CasualWorkers.EarningPerDay, "
				+         "		  CasualWorkers.WorkDay, "
				+ 		  "		  CasualWorkers.PersonID "
				+ 		  "from CasualWorkers "
				+ 		  "inner join Persons "
				+         "on Persons.ID = CasualWorkers.PersonID "
				+ 		  "where CasualWorkers.PersonID = ?;";
		
		PreparedStatement statement;
		
		try {
			statement = connection.prepareStatement(sqlQuery);
			
			statement.setLong(1, id);
			
			ResultSet results = statement.executeQuery();
			
			if (results.next()) {
				
				casualWorker = buildCasualWorker(results);
			}
				
			results.close();
			statement.close();
		}		
		catch(Exception e) {
			e.printStackTrace();
		}		
		finally {
			connection.close();
			return casualWorker;
		}
	}
	
	public void update(Long id, CasualWorker casualWorker) throws SQLException {
		
		new PersonDao().update(casualWorker.getPersonId(), new Person(casualWorker.getName(), casualWorker.getType(), casualWorker.getYearOfBirth()));
		
		if (connection.isClosed()) this.connection = new ConnectionFactory().getConnection();
		
		String sqlQuery = "update CasualWorkers "
						+ "set EarningPerDay = ?, "
						+ "    WorkDay = ? "
						+ "where PersonID = ?";
		
		System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
		System.out.println(">  Update casualWorker started");
		
		PreparedStatement statement;
		
		try {
			statement = connection.prepareStatement(sqlQuery);

            //	Replacing "?" through values
            statement.setFloat(1, casualWorker.getEarningPerDay());
            statement.setInt(2, casualWorker.getWorkDay());
            statement.setLong(3, id);
            
            // 	Executing statement
			int rowsUpdated = statement.executeUpdate();
			if (rowsUpdated > 0) {
				System.out.println("   Update casualWorker Successfully");	
			} else {
				System.out.println("   Id doesn't exist");
			}
			
			statement.close();
		}
		
		catch (Exception e) {
	        e.printStackTrace();
		}
		
		finally {
			System.out.println("<  Update casualWorker ended");
			System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
			System.out.println("");
			connection.close();
		}
	}
	
	public void delete(Long id) throws SQLException {

		CasualWorker casualWorker = read(id);
		
		if (connection.isClosed()) this.connection = new ConnectionFactory().getConnection();
		
		String sqlQuery = "delete from CasualWorkers where PersonID = ?;";
		
		System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
		System.out.println(">  Delete casualWorker started");
		
		PreparedStatement statement;
		
		try {
			
			statement = connection.prepareStatement(sqlQuery);
			
			statement.setLong(1, id);
			
			if(casualWorker.getId() != 0) {
				statement.executeUpdate();
				System.out.println("   Delete casualWorker Successfully");	
			} else { 
				System.out.println("   Error: ID NOT FOUND!");
			}
			
			statement.close();
			
			new PersonDao().delete(casualWorker.getId());
		}
		
		catch(Exception e) {
			e.printStackTrace();
		}
		
		finally {
			System.out.println("<  Delete casualWorker ended");
			System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
			System.out.println("");
			connection.close();
		}
		
	}
	
	private CasualWorker buildCasualWorker(ResultSet results) throws SQLException {
		CasualWorker casualWorker = new CasualWorker();
		
		casualWorker.setId(results.getLong("ID"));
		casualWorker.setName(results.getString("Name"));
		casualWorker.setType(results.getInt("Type"));
		casualWorker.setYearOfBirth(results.getInt("YearOfBirth"));
		
		casualWorker.setEarningPerDay(results.getFloat("EarningPerDay"));
		casualWorker.setWorkDay(results.getInt("WorkDay"));
		casualWorker.setPersonId(results.getInt("PersonID"));
		
		return casualWorker;
	}
}
