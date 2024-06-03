package Email;

import javax.mail.*;
import javax.mail.MessagingException;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

public class EmailSender  {
   public static void main(String[] args) {
      // اطلاعات اتصال به سرویس ایمیل
      String host = "smtp.gmail.com";
      String username = "mani.abdolalizade@gmail.com";
      String password = "klsa kokk aeuj udnc";

      // تنظیمات ارسال ایمیل
      Properties properties = new Properties();
      properties.put("mail.smtp.auth", "true");
      properties.put("mail.smtp.starttls.enable", "true");
      properties.put("mail.smtp.host", host);
      properties.put("mail.smtp.port", "587");

      // ایجاد جلسه اتصال
      Session session = Session.getInstance(properties, new Authenticator() {
         @Override
         protected PasswordAuthentication getPasswordAuthentication() {
            return new PasswordAuthentication(username, password);
         }
      });

      try {
         // ایجاد پیام
         Message message = new MimeMessage(session);
         message.setFrom(new InternetAddress(username));
         message.setRecipients(Message.RecipientType.TO, InternetAddress.parse("java2913@gmail.com"));
         message.setSubject("موضوع ایمیل");
         message.setText("############");

         // ارسال ایمیل
         Transport.send(message);
         System.out.println("ایمیل با موفقیت ارسال شد.");
      } catch (MessagingException e) {
         e.printStackTrace();
      }
   }
}





//
//
//import java.util.Properties;
//import javax.mail.*;
//import javax.mail.MessagingException;
//import javax.mail.internet.*;
///*
// */
//public class EmailSender {
//
//   /**
//    * @param args the command line arguments
//    */
//   public static void main(String[] args) {
//      // TODO code application logic here
//      String host="smtp.gmail.com";
//      final String user="mani.abdolalizade@gmail.com";//change accordingly
//      final String password="adbhaadrMani@2004";//change accordingly
//
//      String to="mani.azar.1383.1351@gmail.com";//change accordingly
//
//      //Get the session object
//      Properties props = new Properties();
//      props.put("mail.smtp.host",host);
//      props.put("mail.smtp.auth", "true");
//      Session session = Session.getDefaultInstance(props, new javax.mail.Authenticator() {
//           protected PasswordAuthentication getPasswordAuthentication() {
//              return new PasswordAuthentication(user,password);
//           }
//        });
//
//      //Compose the message
//      try {
//         MimeMessage message = new MimeMessage(session);
//         message.setFrom(new InternetAddress(user));
//         message.addRecipient(Message.RecipientType.TO,new InternetAddress(to));
//         message.setSubject("javatpoint");
//         message.setText("This is simple program of sending email using JavaMail API");
//
//         //send the message
//         Transport.send(message);
//
//         System.out.println("message sent successfully...");
//
//      } catch (MessagingException e) {e.printStackTrace();}
//   }
//
//}
//
//
//
//




//import java.util.*;
//import javax.mail.*;
//import javax.mail.MessagingException;
//import javax.mail.internet.*;
//import javax.activation.*;
//
//public class EmailSender {
//
//   public static void main(String [] args) {
//      // Recipient's email ID needs to be mentioned.
//      String to = "mani.azar.1383.1351@gmail.com";
//
//      // Sender's email ID needs to be mentioned
//      String from = "mani.abdolalizade@gmail.com";
//
//      // Assuming you are sending email from localhost
//      String host = "localhost";
//
//      // Get system properties
//      Properties properties = System.getProperties();
//
//      // Setup mail server
//      properties.setProperty("mail.smtp.host", host);
//
//      // Get the default Session object.
//      Session session = Session.getDefaultInstance(properties);
//
//      try {
//         // Create a default MimeMessage object.
//         MimeMessage message = new MimeMessage(session);
//
//         // Set From: header field of the header.
//         message.setFrom(new InternetAddress(from));
//
//         // Set To: header field of the header.
//         message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
//
//         // Set Subject: header field
////         Object Line = null;
////         message.setSubject(Subject Line!");
//
//         // Now set the actual message
//         message.setText("actual message");
//
//         // Send message
//         Transport.send(message);
//         System.out.println("Sent message successfully....");
//      } catch (MessagingException mex) {
//         mex.printStackTrace();
//      }    } }