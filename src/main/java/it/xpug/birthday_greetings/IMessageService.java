package it.xpug.birthday_greetings;

import javax.mail.MessagingException;
import javax.mail.internet.AddressException;

public interface IMessageService {
	void send(Greetings greeting ) throws AddressException, MessagingException;
}
