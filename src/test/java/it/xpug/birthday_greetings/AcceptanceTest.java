package it.xpug.birthday_greetings;

import static org.junit.Assert.*;

import org.junit.*;

import com.dumbster.smtp.*;

import it.xpug.birthday_greetings.BirthdayService;
import it.xpug.birthday_greetings.XDate;


public class AcceptanceTest {

	private static final String HOST = "localhost";
	private static final int NONSTANDARD_PORT = 9999;
	private BirthdayService service;
	private IEmployeeRepository employeeRepository;
	private SmtpMailService messageSender;
	private SimpleSmtpServer mailServer;

	@Before
	public void setUp() throws Exception {
		employeeRepository  = new FileEmployeeRepository("employee_data.txt");
		messageSender = new  SmtpMailService(HOST,NONSTANDARD_PORT);
		mailServer = SimpleSmtpServer.start(NONSTANDARD_PORT);
		service = new BirthdayService(employeeRepository,messageSender);
	}

	@After
	public void tearDown() throws Exception {
		mailServer.stop();
		Thread.sleep(200);
	}

	@Test
	public void willSendGreetings_whenItsSomebodysBirthday() throws Exception {

		service.sendGreetings(new XDate("2008/10/08"));
		assertEquals("message not sent?", 1, mailServer.getReceivedEmailSize());
		SmtpMessage message = (SmtpMessage) mailServer.getReceivedEmail().next();
		assertEquals("Happy Birthday, dear John", message.getBody());
		assertEquals("Happy Birthday!", message.getHeaderValue("Subject"));
		String[] recipients = message.getHeaderValues("To");
		assertEquals(1, recipients.length);
		assertEquals("john.doe@foobar.com", recipients[0].toString());
	}

	@Test
	public void willNotSendEmailsWhenNobodysBirthday() throws Exception {
		service.sendGreetings(new XDate("2008/01/01"));
		assertEquals("what? messages?", 0, mailServer.getReceivedEmailSize());
	}
}

