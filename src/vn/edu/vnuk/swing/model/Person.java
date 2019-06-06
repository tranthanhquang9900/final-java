package vn.edu.vnuk.swing.model;

public class Person {
	protected long id;
	protected int yearOfBirth;
	protected int type;
	protected String name;
	
	public Person() {
		
	}
	
	public Person(String name, int type, int yearOfBirth) {
		this.name = name;
		this.type = type;
		this.yearOfBirth = yearOfBirth;
	}
	
	public Person(int id) {
		this.id = id;
	}

	public Person(int id, int type) {
		this.id = id;
		this.type = type;
	}
	
	public Person(int id, int yearOfBirth, int type, String name) {
		this.id = id;
		this.yearOfBirth = yearOfBirth;
		this.type = type;
		this.name = name;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public int getYearOfBirth() {
		return yearOfBirth;
	}

	public void setYearOfBirth(int yearOfBirth) {
		this.yearOfBirth = yearOfBirth;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	@Override
	public String toString() {
		return "Person [id=" + id + ", yearOfBirth=" + yearOfBirth + ", type=" + type + ", name=" + name + "]";
	}

	public float getSalary() { return 0;}
}

