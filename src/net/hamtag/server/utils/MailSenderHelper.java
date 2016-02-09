package net.hamtag.server.utils;

import java.util.Date;
import java.util.Properties;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class MailSenderHelper {
	private String SMTP_PORT = "465";
	private String SSL_FACTORY = "javax.net.ssl.SSLSocketFactory";
	private String SMTP_HOST_NAME = "smtp.gmail.com";
	private Properties smtpProperties;

	public MailSenderHelper() {
		initProperties();
	}

	private void initProperties() {
		smtpProperties = new Properties();
		smtpProperties.put("mail.smtp.host", SMTP_HOST_NAME);
		smtpProperties.put("mail.smtp.auth", "true");
		smtpProperties.put("mail.debug", "true");
		smtpProperties.put("mail.smtp.port", SMTP_PORT);
		smtpProperties.put("mail.smtp.socketFactory.port", SMTP_PORT);
		smtpProperties.put("mail.smtp.socketFactory.class", SSL_FACTORY);
		smtpProperties.put("mail.smtp.socketFactory.fallback", "false");
	}

/*	public static void main(String args[]) {
		String to = "mohamad.kav@gmail.com";
		String from = "hamtag.bot@gmail.com";
		String pwd = "@n0th3rHamtag";
		String subject = "کد فعالسازی";
		String body = "232323";
		send(to, from, pwd, subject, body);
	}*/

	public static boolean send(String to, final String from, final String pwd, String subject, String body) {
		MailSenderHelper tjm = new MailSenderHelper();
		try {
			Properties props = tjm.getSmtpProperties();

			Session session = Session.getDefaultInstance(props, new javax.mail.Authenticator() {
				protected PasswordAuthentication getPasswordAuthentication() {
					return new PasswordAuthentication(from, pwd);
				}
			});
			// -- Create a new message --
			Message msg = new MimeMessage(session);
			// -- Set the FROM and TO fields --
			msg.setFrom(new InternetAddress(from));
			msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to, false));
			// -- Set the subject and body text --
			msg.setSubject(subject);
			msg.setText(body);
			msg.setSentDate(new Date());
			// -- Send the message –
			Transport.send(msg);
			return true;
		} catch (Exception ex) {
			ex.printStackTrace();
			return false;
		}
	}

	public Properties getSmtpProperties() {
		return smtpProperties;
	}

	public void setSmtpProperties(Properties smtpProperties) {
		this.smtpProperties = smtpProperties;
	}
}