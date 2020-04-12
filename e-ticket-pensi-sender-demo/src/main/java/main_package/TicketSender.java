package main_package;

import java.sql.SQLException;

import javax.mail.MessagingException;
import javax.mail.internet.AddressException;

public class TicketSender {
	private Customer customer;

	public TicketSender(Customer customer) {
		this.customer = customer;
	}

	private void sendDataToDatabase(Customer customer, Ticket ticket) {
		CustomerDao customerDao = new CustomerDao();
		TicketDao ticketDao = new TicketDao();
		try {
			customerDao.insertCustomer(customer);
			ticketDao.insertTicket(ticket, customerDao.getCustomerId());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void sendTicket() {
		Ticket ticket = new Ticket(customer.getCustomerFullName(), customer.getCustomerPhoneNumber());
		Mailer mailer = new Mailer(customer.getCustomerEmailAddress(), "email",
				"password", "Test mail subject", "Test mail text");

		BarcodeGenerator.generateBarcode(ticket.getTicket());
		sendDataToDatabase(customer, ticket);
		try {
			mailer.sendMail();
		} catch (AddressException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (MessagingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
