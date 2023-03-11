package com.email.job;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import jakarta.mail.Message;
import jakarta.mail.MessagingException;
import jakarta.mail.Session;
import jakarta.mail.Transport;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;

@Component
public class EmailSendingJob {
	private static final SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");
	private static final Logger logger = LoggerFactory.getLogger(EmailSendingJob.class);

	@Scheduled(fixedRate = 100000)
	public void sendemail() {
		logger.info("hello ...................");
	}
/*	@Scheduled(fixedRate = 1000)
	public  void MyGETRequest() throws IOException {
	    URL urlForGetRequest = new URL("https://icanhazdadjoke.com/slack");
	    String readLine = null;
	    HttpURLConnection conection = (HttpURLConnection) urlForGetRequest.openConnection();
	    conection.setRequestMethod("GET");
	    int responseCode = conection.getResponseCode();


	    if (responseCode == HttpURLConnection.HTTP_OK) {
	        BufferedReader in = new BufferedReader(
	            new InputStreamReader(conection.getInputStream()));
	        StringBuffer response = new StringBuffer();
	        
	        while ((readLine = in .readLine()) != null) {
	            response.append(readLine);
	        } in .close();
	        // print result
	        logger.info("JSON String Result " + response.toString());
	        //GetAndPost.POSTRequest(response.toString());
	    } else {
	    	logger.info("GET NOT WORKED");
	    }
}
	@Scheduled(fixedRate = 1000)
	public void reportCurrentTime() {
		logger.info("The time is now {}", dateFormat.format(new Date()));
	}

	@Scheduled(fixedRate = 1000000000)
	public static void sendnewmail() {
		String to = "avinashkushwaha06@gmail.com";// change accordingly
		String from = "avinashkushwaha06@gmail.com";// change accordingly
		// String host = "localhost";//or IP address
		String host = "192.168.0.1";

		// Get the session object
		Properties properties = System.getProperties();
		properties.setProperty("mail.smtp.host", host);
		Session session = Session.getDefaultInstance(properties);

		// compose the message
		try {
			MimeMessage message = new MimeMessage(session);
			message.setFrom(new InternetAddress(from));
			message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
			message.setSubject("Ping");
			message.setText("Hello, this is example of sending email  ");

			// Send message
			Transport.send(message);
			logger.info("message sent successfully....");

		} catch (MessagingException mex) {
			mex.printStackTrace();
		}
	}
	@Scheduled(fixedRate = 100000000)
	public void joks() {
	String response = "https://icanhazdadjoke.com/api";
	Properties properties = System.getProperties();
	properties.setProperty(response, response);
	Session session = Session.getDefaultInstance(properties);
	logger.info("Api respone"+ session);
	}*/
	
}
