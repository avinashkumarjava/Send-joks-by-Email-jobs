package com.email.job;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Properties;

import org.apache.tomcat.util.json.ParseException;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
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
public class Joks {
	private static final SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");
	private static final Logger logger = LoggerFactory.getLogger(Joks.class);

	@Scheduled(fixedRate = 10000)
	public void Joks() throws IOException, JSONException, ParseException {
		String Joks = "";
		URL urlForGetRequest = new URL("https://icanhazdadjoke.com/slack");
		String readLine = null;
		HttpURLConnection conection = (HttpURLConnection) urlForGetRequest.openConnection();
		conection.setRequestMethod("GET");
		int responseCode = conection.getResponseCode();

		if (responseCode == HttpURLConnection.HTTP_OK) {
			BufferedReader in = new BufferedReader(new InputStreamReader(conection.getInputStream()));
			StringBuffer response = new StringBuffer();
			while ((readLine = in.readLine()) != null) {
				response.append(readLine);
			}
			in.close();
			String resp = response.toString();
			JSONObject json = new JSONObject(resp);
			JSONArray jsonArray = json.getJSONArray("attachments");
			for (int i = 0; i < jsonArray.length(); i++) {
				JSONObject explrObject = jsonArray.getJSONObject(i);
				Joks = explrObject.getString("text");
				logger.info("Joks --> " + Joks);
			}
			emailsending(Joks);
		} else {
			logger.info("GET NOT WORKED");

		}
	}

	public void emailsending(String Joks) {
		// Recipient's email ID needs to be mentioned.
		String to = "avinashkushwaha06@gmail.com";

		// Sender's email ID needs to be mentioned
		String from = "avinashkushwaha06@gmail.com";

		// Assuming you are sending email from localhost
		String host = "localhost";

		// Get system properties
		Properties properties = System.getProperties();

		// Setup mail server
		properties.setProperty("mail.smtp.host", host);

		// Get the default Session object.
		Session session = Session.getDefaultInstance(properties);

		try {
			// Create a default MimeMessage object.
			MimeMessage message = new MimeMessage(session);

			// Set From: header field of the header.
			message.setFrom(new InternetAddress(from));

			// Set To: header field of the header.
			message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));

			// Set Subject: header field
			message.setSubject("Joks...........");

			// Now set the actual message
			message.setText(Joks);

			// Send message
			Transport.send(message);
			System.out.println("Sent message successfully....");
		} catch (MessagingException mex) {
			mex.printStackTrace();
		}
	}
}
