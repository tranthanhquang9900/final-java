package vn.edu.vnuk.swing.model;

public class CasualWorker extends Person {
	private int workDay;
	private float earningPerDay;
	private long personId;
	
	public int getWorkDay() {
		return workDay;
	}

	public void setWorkDay(int workDay) {
		this.workDay = workDay;
	}

	public float getEarningPerDay() {
		return earningPerDay;
	}

	public void setEarningPerDay(float earningPerDay) {
		this.earningPerDay = earningPerDay;
	}
	
	public long getPersonId() {
		return personId;
	}

	public void setPersonId(long personId) {
		this.personId = personId;
	}

	@Override
	public String toString() {
		return "CasualWorker [workDay=" + workDay + ", earningPerDay=" + earningPerDay + ", id=" + id + ", yearOfBirth="
				+ yearOfBirth + ", type=" + type + ", name=" + name + "]";
	}

	@Override
	public float getSalary() {
		float salary = workDay * earningPerDay;
		return salary;
	}

}
