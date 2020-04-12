package main_package;

import java.util.Random;

public class Ticket {
	private String customerFullName;
	private String customerPhoneNumber;
	private String ticketDigits;
	private String ticket;
	
	public Ticket(String customerFullName, String customerPhoneNumber) {
		this.customerFullName = customerFullName;
		this.customerPhoneNumber = customerPhoneNumber;
		ticketDigits = generateTicketDigits();
		ticket = customerPhoneNumber + ";" + ticketDigits;
	}
	
	private String generateTicketDigits() {
		Random r = new Random();

		String ticketDigits = "";

		for (int i = 0; i < 6; i++) {
			ticketDigits += Integer.toString(r.nextInt(10));
		}

		return ticketDigits;
	}

	public String getCustomerFullName() {
		return customerFullName;
	}

	public String getCustomerPhoneNumber() {
		return customerPhoneNumber;
	}

	public String getTicketDigits() {
		return ticketDigits;
	}

	public String getTicket() {
		return ticket;
	}

}
