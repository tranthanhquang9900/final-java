package vn.edu.vnuk.swing.define;

import java.util.ArrayList;

import vn.edu.vnuk.swing.model.Person;

public class Define {
	public static final int TYPE_OF_LECTURER = 0;
	public static final int TYPE_OF_STAFF = 1;
	public static final int TYPE_OF_CASUAL_WORKER = 2;
	
	public static final String NAME_OF_LECTURER = "LECTURER";
	public static final String NAME_OF_STAFF = "STAFF";
	public static final String NAME_OF_CASUAL_WORKER = "CASUAL WORKER";
	
	public static final float DEFAULT_MINIMUM_WAGE = 730;
	
	public static int latestId = 0;
	
	public static final int ALLOWANCE_OF_BACHELOR = 300;
	public static final int ALLOWANCE_OF_MASTER = 900;
	public static final int ALLOWANCE_OF_DOCTOR = 2000;
	
	public static final int ALLOWANCE_OF_CHIEF = 1000;
	public static final int ALLOWANCE_OF_DEPUTY = 600;
	public static final int ALLOWANCE_OF_EMPLOYEE = 400;
	
	public static final int TYPE_OF_BACHELOR = 0;
	public static final int TYPE_OF_MASTER = 1;
	public static final int TYPE_OF_DOCTOR = 2;
	
	public static final int TYPE_OF_CHIEF = 0;
	public static final int TYPE_OF_DEPUTY = 1;
	public static final int TYPE_OF_EMPLOYEE = 2;
	
	public static final String QUALIFICATION_OF_BACHELOR = "BACHELOR";
	public static final String QUALIFICATION_OF_MASTER = "MASTER";
	public static final String QUALIFICATION_OF_DOCTOR = "DOCTOR";
	
	public static final String POSITION_OF_CHIEF = "CHIEF";
	public static final String POSITION_OF_DEPUTY = "DEPUTY";
	public static final String POSITION_OF_EMPLOYEE = "EMPLOYEE";
	
	public static float newMinimumWage = DEFAULT_MINIMUM_WAGE;
	
	public static ArrayList <Person> persons = new ArrayList <>();
	
	public static final int TYPE_OF_ACTION_CREATE = 0;
	public static final int TYPE_OF_ACTION_EDIT = 1;
}
