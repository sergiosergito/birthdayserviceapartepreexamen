package it.xpug.birthday_greetings;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.ParseException;
import java.util.List;

import javax.mail.MessagingException;
import javax.mail.internet.AddressException;

public class BirthdayService {
	
	private IEmployeeRepository respository;
	private IMessageService messageService;
	
	BirthdayService(IEmployeeRepository resposity, IMessageService messageService){
		this.respository = resposity;
		this.messageService = messageService;
	}

	public void sendGreetings(XDate xDate) throws FileNotFoundException, IOException, 
	ParseException, AddressException, MessagingException {
		
		List<Employee> employees =  respository.findEmployeesWhoseBirthdayIs(xDate);
		for (Employee employee: employees) {
			Greetings greetings = new Greetings(employee);
			messageService.send(greetings);
		}
		
	}

}
