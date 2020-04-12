package main_package;

import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

public class Mailer {
	private String recipient;
	private String senderMail;
	private String mailPassword;
	private String messageSubject;
	private String messageText;

	public Mailer(String recipient, String senderMail, String mailPassword, 
			String messageSubject, String messageText) {
		this.recipient = recipient;
		this.senderMail = senderMail;
		this.mailPassword = mailPassword;
		this.messageSubject = messageSubject;
		this.messageText = messageText;
	}
	
	public void sendMail() throws AddressException, MessagingException {
			Properties properties = new Properties();

			properties.put("mail.smtp.auth", "true");
			properties.put("mail.smtp.starttls.enable", "true");
			properties.put("mail.smtp.host", "smtp.gmail.com");
			properties.put("mail.smtp.port", "587");

			Session session = Session.getInstance(properties, new Authenticator() {
				@Override
				protected PasswordAuthentication getPasswordAuthentication() {
					return new PasswordAuthentication(senderMail, mailPassword);
				}
			});

			Message message = prepareMessage(session);
			Transport.send(message);
	
	}

	private Message prepareMessage(Session session) throws AddressException, MessagingException {
			Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress(senderMail));
			message.setRecipient(Message.RecipientType.TO, new InternetAddress(recipient));
			message.setSubject(messageSubject);

			Multipart multipart = new MimeMultipart();

			MimeBodyPart textBodyPart = new MimeBodyPart();
			textBodyPart.setText(messageText);

			MimeBodyPart attachmentBodyPart = new MimeBodyPart();
			DataSource source = new FileDataSource(
					"D:\\E-TicketPensi\\e-ticket-pensi-sender-demo\\qrcode.png");
			attachmentBodyPart.setDataHandler(new DataHandler(source));
			attachmentBodyPart.setFileName("qrcode.png");

			multipart.addBodyPart(textBodyPart);
			multipart.addBodyPart(attachmentBodyPart);

			message.setContent(multipart);

			return message;	
	}

}
