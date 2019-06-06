package vn.edu.vnuk.swing.util;

import vn.edu.vnuk.swing.define.Define;

public class CommonUtils {
	
	public static String getTypeString(int type) {
		String typeString = "";
		switch (type) {
		case Define.TYPE_OF_LECTURER: {
			typeString = Define.NAME_OF_LECTURER;
			break;
		}
		
		case Define.TYPE_OF_STAFF: {
			typeString = Define.NAME_OF_STAFF;
			break;
		}
		
		case Define.TYPE_OF_CASUAL_WORKER:{
			typeString = Define.NAME_OF_CASUAL_WORKER;
			break;
		}
		}
		
		return typeString;
	}
	
	public static Object[][] sortByName(Object[][] objects) {
		Object[] temp;
		for (int i = 0; i < objects.length; i++) {
			for (int j = i + 1; j < objects.length; j++) {
				if (String.valueOf(objects[i][2]).compareTo(String.valueOf(objects[j][2])) > 0) {
					temp = objects[i];
					objects[i] = objects[j];
					objects[j] = temp;
				}
			}
		}
		
		return objects;
	}
	
	public static Object[][] sortBySalary(Object[][] objects) {
		Object[] temp;
		for (int i = 0; i < objects.length; i++) {
			for (int j = i + 1; j < objects.length; j++) {
				if (Float.valueOf(String.valueOf(objects[i][3])) > Float.valueOf(String.valueOf(objects[j][3]))) {
					temp = objects[i];
					objects[i] = objects[j];
					objects[j] = temp;
				}
			}
		}
		
		return objects;
	}
	
}