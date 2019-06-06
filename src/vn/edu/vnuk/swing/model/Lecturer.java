package vn.edu.vnuk.swing.model;

public class Lecturer extends Person {	
	private String hometown;
	private String department;
	private String qualification;
	private int allowance;
	private int periodsInMonth;
	private float salaryRatio;
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

	public String getQualification() {
		return qualification;
	}

	public void setQualification(String qualification) {
		this.qualification = qualification;
	}

	public int getAllowance() {
		return allowance;
	}

	public void setAllowance(int allowance) {
		this.allowance = allowance;
	}

	public int getPeriodsInMonth() {
		return periodsInMonth;
	}

	public void setPeriodsInMonth(int periodsInMonth) {
		this.periodsInMonth = periodsInMonth;
	}

	public float getSalaryRatio() {
		return salaryRatio;
	}

	public void setSalaryRatio(float salaryRatio) {
		this.salaryRatio = salaryRatio;
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
		float salary = (salaryRatio * minimumWage) + allowance + (periodsInMonth * 45);
		return salary;
	}

	@Override
	public String toString() {
		return "Lecturer [hometown=" + hometown + ", department=" + department + ", qualification=" + qualification
				+ ", allowance=" + allowance + ", periodsInMonth=" + periodsInMonth + ", salaryRatio=" + salaryRatio
				+ ", yearOfWork=" + yearOfWork + ", minimumWage=" + minimumWage + ", personId=" + personId + "]";
	}
	
}
