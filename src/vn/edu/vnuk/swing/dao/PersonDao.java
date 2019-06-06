package vn.edu.vnuk.swing.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import vn.edu.vnuk.swing.define.Define;
import vn.edu.vnuk.swing.jdbc.ConnectionFactory;
import vn.edu.vnuk.swing.model.CasualWorker;
import vn.edu.vnuk.swing.model.Lecturer;
import vn.edu.vnuk.swing.model.Person;
import vn.edu.vnuk.swing.model.Staff;

public class PersonDao {
	private Connection connection;

	public PersonDao() {
		this.connection = new ConnectionFactory().getConnection();
	}
	
	@SuppressWarnings("finally")
	public long create(Person person) throws SQLException {
	
		if (connection.isClosed()) this.connection = new ConnectionFactory().getConnection();
		
		long idReturned = -1;
		
		String sqlQuery = "insert into Persons (Name, Type, YearOfBirth) "
                + "values (?, ?, ?);";
		
		System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
		System.out.println(">  Insert person started");
		
		PreparedStatement statement;
		
		try {
			statement = connection.prepareStatement(sqlQuery, Statement.RETURN_GENERATED_KEYS);

            //	Replacing "?" through values
            statement.setString(1, person.getName());
            statement.setInt(2, person.getType());
            statement.setInt(3, person.getYearOfBirth());
            
            // 	Executing statement
            statement.execute();
			
            ResultSet generatedKeys = statement.getGeneratedKeys();
			if (generatedKeys.next()) {
				idReturned = generatedKeys.getLong(1);
				System.out.println("   DATA successfully loaded in \'persons\'");
			} else {
				System.out.println("   Creating user failed, no ID obtained.");
			}
			
			statement.close();
		}
		
		catch (Exception e) {
	        e.printStackTrace();
		}
		
		finally {
			System.out.println("<  Insert person ended");
			System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
			System.out.println("");
			connection.close();
			return idReturned;
		}
	}
	
	@SuppressWarnings("finally")
	public List<Person> read(String keyword) throws SQLException {

		if (connection.isClosed()) this.connection = new ConnectionFactory().getConnection();
		
		List<Person> persons = new ArrayList<Person>();
		String sqlQuery = "select * from Persons where name LIKE '%" + keyword + "%';";
	
		System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
		System.out.println(">  Read persons started");
		
		PreparedStatement statement;
		
		try {			
			statement = connection.prepareStatement(sqlQuery);
			
			ResultSet results = statement.executeQuery();
			
			while(results.next()) {
				Person person = buildPerson(results);
				switch(person.getType()) {
					case Define.TYPE_OF_STAFF: {
						Staff staff = new Staff();
						staff = new StaffDao().read(person.getId());
						staff.setId(person.getId());
						staff.setType(person.getType());
						staff.setName(person.getName());
						staff.setYearOfBirth(person.getYearOfBirth());
						persons.add(staff);
						break;
					}
					
					case Define.TYPE_OF_LECTURER: {
						Lecturer lecturer = new Lecturer();
						lecturer = new LecturerDao().read(person.getId());
						lecturer.setId(person.getId());
						lecturer.setType(person.getType());
						lecturer.setName(person.getName());
						lecturer.setYearOfBirth(person.getYearOfBirth());
						persons.add(lecturer);
						break;
					}
					
					case Define.TYPE_OF_CASUAL_WORKER: {
						CasualWorker casualWorker = new CasualWorker();
						casualWorker = new CasualWorkerDao().read(person.getId());
						casualWorker.setId(person.getId());
						casualWorker.setType(person.getType());
						casualWorker.setName(person.getName());
						casualWorker.setYearOfBirth(person.getYearOfBirth());
						persons.add(casualWorker);
						break;
					}
				}
			}
			
			results.close();
			statement.close();
		}		
		catch (Exception e) {
	        e.printStackTrace();
		}		
		finally {
			System.out.println("<  Read persons ended");
			System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
			System.out.println("");
			connection.close();
			return persons;
		}
	}
	
	@SuppressWarnings("finally")
	public Person read(Long id) throws SQLException {

		if (connection.isClosed()) this.connection = new ConnectionFactory().getConnection();
		
		Person person = new Person();
		
		String sqlQuery = "select * from Persons where id  = ?;";
		
		PreparedStatement statement;
		
		try {
			statement = connection.prepareStatement(sqlQuery);
			
			statement.setLong(1, id);
			
			ResultSet results = statement.executeQuery();
			
			if (results.next()) {
				
				person = buildPerson(results);
			}
				
			results.close();
			statement.close();
		}		
		catch(Exception e) {
			e.printStackTrace();
		}		
		finally {
			connection.close();
			return person;
		}
	}
	
	public void update(Long id, Person person) throws SQLException {

		if (connection.isClosed()) this.connection = new ConnectionFactory().getConnection();
		
		String sqlQuery = "update Persons "
						+ "set Name = ?,"
						+ "	   Type = ?,"
						+ "    YearOfBirth = ? "
						+ "where id = ?";
		
		System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
		System.out.println(">  Update person started");
		
		PreparedStatement statement;
		
		try {
			statement = connection.prepareStatement(sqlQuery);

            //	Replacing "?" through values
            statement.setString(1, person.getName());
            statement.setInt(2, person.getType());
            statement.setInt(3, person.getYearOfBirth());
            statement.setLong(4, id);

            // 	Executing statement
			int rowsUpdated = statement.executeUpdate();
			if (rowsUpdated > 0) {
				System.out.println("   Update person Successfully");	
			} else {
				System.out.println("   Id doesn't exist");
			}
			
			statement.close();
		}
		
		catch (Exception e) {
	        e.printStackTrace();
		}
		
		finally {
			System.out.println("<  Update person ended");
			System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
			System.out.println("");
			connection.close();
		}
	}
	
	public void delete(Long id) throws SQLException {

		Person person = read(id);
		
		if (connection.isClosed()) this.connection = new ConnectionFactory().getConnection();
		
		String sqlQuery = "delete from Persons where id = ?;";
		
		System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
		System.out.println(">  Delete person started");
		
		PreparedStatement statement;
		
		try {
			
			statement = connection.prepareStatement(sqlQuery);
			
			statement.setLong(1, id);
			
			if(person.getId() != 0) {
				statement.executeUpdate();
				System.out.println("   Delete person Successfully");	
			} else { 
				System.out.println("   Error: ID NOT FOUND!");
			}
			
			statement.close();
		}
		
		catch(Exception e) {
			e.printStackTrace();
		}
		
		finally {
			System.out.println("<  Delete person ended");
			System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
			System.out.println("");
			connection.close();
		}
		
	}
	
	private Person buildPerson(ResultSet results) throws SQLException {
		Person person = new Person();
		
		person.setId(results.getLong("ID"));
		person.setName(results.getString("Name"));
		person.setType(results.getInt("Type"));
		person.setYearOfBirth(results.getInt("YearOfBirth"));
		
		return person;
	}
}
