package ca.uwaterloo.cs.webscraping;


import javax.mail.*;
import javax.mail.internet.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class EmailUtil {

    public void sendNewsletter(List<String> subscribers, List<String> foodMenus) {

        //connect to email server
        Properties prop = new Properties();
        prop.put("mail.smtp.auth", FoodNewsletterConstants.MAIL_SERVER_AUTH);
        prop.put("mail.smtp.starttls.enable", FoodNewsletterConstants.MAIL_SERVER_TLS_ENABLED);
        prop.put("mail.smtp.host", FoodNewsletterConstants.MAIL_SERVER_HOST);
        prop.put("mail.smtp.port", FoodNewsletterConstants.MAIL_SERVER_PORT);
        prop.put("mail.smtp.ssl.protocols", FoodNewsletterConstants.MAIL_SERVER_TLS_VERSION);

        Session session = Session.getInstance(prop, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(FoodNewsletterConstants.MAIL_SERVER_USERNAME, FoodNewsletterConstants.MAIL_SERVER_PASSWORD);
            }
        });

        //composite email content
        Message message = new MimeMessage(session);
        try {
            //set from email
            message.setFrom(new InternetAddress("michaelymq@gmail.com"));
            //set recipient email address
            for (String recipient:subscribers) {
                message.addRecipient(Message.RecipientType.TO, new InternetAddress(recipient));
            }
            //set email title
            LocalDate dateObj = LocalDate.now();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            String date = dateObj.format(formatter);
            message.setSubject(date + " food menus");
            //set email body
            StringBuffer messageBuffer = new StringBuffer();
            for (String item : foodMenus) {
                messageBuffer.append("<li>" + item + "</li>");
            }
            MimeBodyPart mimeBodyPart = new MimeBodyPart();
            mimeBodyPart.setContent(messageBuffer.toString(), "text/html; charset=utf-8");
            Multipart multipart = new MimeMultipart();
            multipart.addBodyPart(mimeBodyPart);
            message.setContent(multipart);
            Transport.send(message);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
