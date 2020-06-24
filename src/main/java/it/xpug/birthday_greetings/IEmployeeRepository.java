package it.xpug.birthday_greetings;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.ParseException;
import java.util.List;


public interface IEmployeeRepository {

	List<Employee> findEmployeesWhoseBirthdayIs(XDate today)  throws IOException, ParseException,FileNotFoundException;
}
