package it.xpug.birthday_greetings;

public class Greetings {
	private String subject;
	private String message;
	private Employee employee;
	public Greetings(Employee employee) {
		this.employee = employee;
	}
	
	public Greetings birthday() {
		this.setSubject("Happy Birthday!");
		this.setMessage("Happy Birthday, dear %NAME%!".replace("%NAME%", employee.getFirstName()));
		return this;
	}
	
	public String getMail() {
		return employee.getEmail();
	}
	
	public String getMessage() {
		return message;
	}
	
	private void setMessage(String message) {
		this.message = message;
		
	}
	
	public String getSubject() {
		return subject;
	}
	
	
	private void setSubject(String subject) {
		this.subject = subject;
		
	}

	
}
