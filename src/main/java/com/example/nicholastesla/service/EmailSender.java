package com.example.nicholastesla.service;
import jakarta.mail.*;
import jakarta.mail.internet.*;
import java.util.Properties;

public class EmailSender  {
    public static void sendEmail(String toEmail, String subject, String messageContent) {
        // Sender's email credentials
        final String fromEmail = "lakshmisravanthi8179@gmail.com"; // your email ID
        final String password = "Lakshmi@8179"; // your password

        // SMTP server  information
        String host = "smtp.gmail.com" ; // Gmail SMTP server

        // Setting up mail server properties
        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", host);
        props.put("mail.smtp.port", "587");

        // Create an Authenticator object with email and password
        Authenticator auth = new Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(fromEmail, password);
            }
        };

        // Create a mail session with the Authenticator
        Session session = Session.getInstance(props, auth);

        try {
            // Create and configure the email message
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(fromEmail));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(toEmail));
            message.setSubject(subject);
            message.setText(messageContent);

            // Send the email
            Transport.send(message);
            System.out.println("Email sent successfully!");

        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }
}
