package it.xpug.birthday_greetings;

import java.io.*;
import java.text.ParseException;

import javax.mail.*;
import javax.mail.internet.*;

public class Main {

	public static void main(String[] args) throws AddressException, IOException, ParseException, MessagingException {
		IEmployeeRepository repository = new FileEmployeeRepository("employee_data.txt");
		IMessageService emailService = new SmtpMailService("localhost",25);
		BirthdayService service = new BirthdayService(repository, emailService);
		service.sendGreetings(new XDate());
	}

}
