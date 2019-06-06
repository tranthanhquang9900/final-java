package vn.edu.vnuk.swing.controller;

import java.sql.SQLException;
import java.util.ArrayList;

import vn.edu.vnuk.swing.dao.PersonDao;
import vn.edu.vnuk.swing.model.Person;

public class Controller {
	
	public ArrayList<Person> getAllPersons(String keyword) throws SQLException {
		return (ArrayList<Person>) new PersonDao().read(keyword);
	}
	
}

