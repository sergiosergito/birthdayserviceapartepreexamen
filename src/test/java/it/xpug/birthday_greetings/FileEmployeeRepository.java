package it.xpug.birthday_greetings;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

public class FileEmployeeRepository implements IEmployeeRepository{

	private String filename;
	public FileEmployeeRepository(String filename) {
		this.filename = filename;
	}
	
	public List<Employee> findEmployeesWhoseBirthdayIs(XDate today) throws IOException, ParseException{
		List<Employee> employees = getAllEmployees();
		return filterEmployeesWhoseBirthdayIs(today,employees);
	}

	private List<Employee> filterEmployeesWhoseBirthdayIs(XDate today, List<Employee> employees) {
		List<Employee> employeesWithBirthdayToday = new ArrayList<Employee>();
		for(Employee employee : employees) {
			if (employee.isBirthday(today)) {
				employeesWithBirthdayToday.add(employee);
			}
		}	
		return null;
	}

	private List<Employee> getAllEmployees() throws IOException, ParseException {
		List<Employee> employees = new ArrayList<Employee>();
		BufferedReader employeesFileReader = createEmployeesFileReader();
		String employeeData = employeesFileReader.readLine();
		while((employeeData = employeesFileReader.readLine()) != null) {
			Employee employee = getEmployee(employeeData);
			employees.add(employee);
		}
		employeesFileReader.close();
		return employees;
	}

	private Employee getEmployee(String str) throws ParseException {
		String[] employeeData = str.split(", ");
		Employee employee = new Employee(employeeData[1], employeeData[0], employeeData[2], employeeData[3]);
		return employee;
	}

	private BufferedReader createEmployeesFileReader() throws FileNotFoundException {
		FileReader fileReader = new FileReader(this.filename);
		return new BufferedReader(fileReader);
	}
}
