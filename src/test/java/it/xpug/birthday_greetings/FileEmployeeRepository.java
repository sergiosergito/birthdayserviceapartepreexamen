package it.xpug.birthday_greetings;

import java.util.List;

public class FileEmployeeRepository {

	private String filename;
	public FileEmployeeRepository(String filename) {
		this.filename = filename;
	}
	
	public List<Employee> findEmployeesWhoseBirthdayIs(XDate today){
		List<Employee> employees = getAllEmployees();
		return filterEmployeesWhoseBirthdayIs(today,employees);
	}

	private List<Employee> filterEmployeesWhoseBirthdayIs(XDate today, List<Employee> employees) {
		// TODO Auto-generated method stub
		return null;
	}

	private List<Employee> getAllEmployees() {
		// TODO Auto-generated method stub
		return null;
	}
}
