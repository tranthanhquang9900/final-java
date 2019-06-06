package vn.edu.vnuk.swing.model;

public class Staff extends Person {
	private String hometown;
	private String department;
	private float salaryRatio;
	private int allowance;
	private String position;
	private int workDay;
	private int yearOfWork;
	private float minimumWage;
	private long personId;
	
	public String getHometown() {
		return hometown;
	}

	public void setHometown(String hometown) {
		this.hometown = hometown;
	}

	public String getDepartment() {
		return department;
	}

	public void setDepartment(String department) {
		this.department = department;
	}

	public float getSalaryRatio() {
		return salaryRatio;
	}

	public void setSalaryRatio(float salaryRatio) {
		this.salaryRatio = salaryRatio;
	}

	public int getAllowance() {
		return allowance;
	}

	public void setAllowance(int allowance) {
		this.allowance = allowance;
	}

	public String getPosition() {
		return position;
	}

	public void setPosition(String position) {
		this.position = position;
	}

	public int getWorkDay() {
		return workDay;
	}

	public void setWorkDay(int workDay) {
		this.workDay = workDay;
	}

	public int getYearOfWork() {
		return yearOfWork;
	}

	public void setYearOfWork(int yearOfWork) {
		this.yearOfWork = yearOfWork;
	}
	
	public float getMinimumWage() {
		return minimumWage;
	}

	public void setMinimumWage(float minimumWage) {
		this.minimumWage = minimumWage;
	}
	
	public void setPersonId(long personId) {
		this.personId = personId;
	}
	
	public long getPersonId() {
		return personId;
	}

	public void setPersonId(int personId) {
		this.personId = personId;
	}

	@Override
	public float getSalary() {
		float salary = (salaryRatio * minimumWage) + allowance + (workDay * 30);
		return salary;
	}

	@Override
	public String toString() {
		return "Staff [hometown=" + hometown + ", department=" + department + ", salaryRatio=" + salaryRatio
				+ ", allowance=" + allowance + ", position=" + position + ", workDay=" + workDay + ", yearOfWork="
				+ yearOfWork + ", minimumWage=" + minimumWage + ", personId=" + personId + "]";
	}
	
}
