package com.qa.reporting;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Date;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import com.qa.logging.Results;


public class EmailReporting extends Object{
	
	public static String EMAILID ;
	
	public void getEmailReporting()
	
	{
		 EMAILID	 = new LoggingProperties()
		   .loadLoggingProperties("C://LSIReports//Properties//Database.properties").getProperty("EMAIL_ID");
	}

	
	
	public String readFile(String fileName) throws IOException {
	    BufferedReader br = new BufferedReader(new FileReader(fileName));
	    try {
	        StringBuilder sb = new StringBuilder();
	        String line = br.readLine();

	        while (line != null) {
	            sb.append(line);
	            sb.append("\n");
	            line = br.readLine();
	        }
	        return sb.toString();
	    } finally {
	        br.close();
	    }
	}
	

public void sendEmail()
{
	
    try{
    	
    	// getEmailReporting();
    	
    	String body = readFile(Results.SummaryHtmlfile);

        Properties props = new Properties();
        props.put("mail.smtp.host", "smtp.gmail.com"); // for gmail use smtp.gmail.com
        props.put("mail.smtp.auth", "true");
        props.put("mail.debug", "true"); 
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.port", "465");
        props.put("mail.smtp.socketFactory.port", "465");
        props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        props.put("mail.smtp.socketFactory.fallback", "false");

        Session mailSession = Session.getInstance(props, new javax.mail.Authenticator() {

            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication("lsiautomationreports@gmail.com", "automation1234");
            }
        });

        mailSession.setDebug(true); // Enable the debug mode

        Message msg = new MimeMessage( mailSession );

        //--[ Set the FROM, TO, DATE and SUBJECT fields
        msg.setFrom( new InternetAddress( "lsiautomationreports@lsiqa.com" ) );
        msg.setRecipients( Message.RecipientType.TO,InternetAddress.parse("EMtesting@liquidityservices.com") );
        msg.setSentDate( new Date());
        msg.setSubject( "Automation Report" );

        //--[ Create the body of the mail
       
        
        msg.setContent(body,"text/html");
        

        //--[ Ask the Transport class to send our mail message
        Transport.send( msg );

    }catch(Exception E){
        System.out.println( "Oops something has gone pearshaped!");
        System.out.println( E);
    }
}
}