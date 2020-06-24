package it.xpug.birthday_greetings;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class SmtpMailService implements IMessageService{

	private final int smtpPort;
	private final String smtpHost;
	private final String SENDER = "sender@here.com";
	
	public SmtpMailService(String host,int port) {
		this.smtpHost = host;
		this.smtpPort = port;
	}
	
	public void send(Greetings greeting) throws AddressException, MessagingException {
		greeting.birthday();
		sendMessage(greeting, SENDER, greeting.getMail());
	}

	private void sendMessage(Greetings greetings, String sender,
			String recipient) throws AddressException, MessagingException 
	{

		Session session = createMailSession();
		Message msg = constructMessage(sender, greetings.getSubject(),greetings.getMessage(), recipient, session);
		Transport.send(msg);
	}

	private Message constructMessage(String sender, String subject,
			String body, String recipient, Session session)
			throws MessagingException, AddressException {
			Message msg = new MimeMessage(session);
			msg.setFrom(new InternetAddress(sender));
			msg.setRecipient(Message.RecipientType.TO, new InternetAddress(
					recipient));
			msg.setSubject(subject);
			msg.setText(body);
			return msg;
	}
	

	private Session createMailSession() {
		java.util.Properties props = new java.util.Properties();
		props.put("mail.smtp.host", smtpHost);
		props.put("mail.smtp.port", "" + smtpPort);
		Session session = Session.getDefaultInstance(props, null);
		return session;
	}

}

