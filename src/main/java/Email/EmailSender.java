package Email;

import javax.mail.*;
import javax.mail.MessagingException;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.swing.*;
import java.util.Properties;

public class EmailSender {


   public String code;


   // اطلاعات اتصال به سرویس ایمیل
   final String host = "smtp.gmail.com";
   final String username = "mani.abdolalizade@gmail.com";
   final String password = "klsa kokk aeuj udnc";
//klsa kokk aeuj udnc
   public  void send (String emailAdress,String code){
      // تنظیمات ارسال ایمیل
      Properties properties = new Properties();
      properties.put("mail.smtp.auth","true");
      properties.put("mail.smtp.starttls.enable","true");
      properties.put("mail.smtp.host",host);
      properties.put("mail.smtp.port","587");

      // ایجاد جلسه اتصال
      Session session = Session.getInstance(properties, new Authenticator() {
         @Override
         protected PasswordAuthentication getPasswordAuthentication() {
            return new PasswordAuthentication(username, password);
         }
      });

      try

      {
         // ایجاد پیام
         Message message = new MimeMessage(session);
         message.setFrom(new InternetAddress(username));
         message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(emailAdress));
         message.setSubject("Forget pass");
         message.setText(code);

         // ارسال ایمیل
         Transport.send(message);
         System.out.println("email sended");
      } catch(
              MessagingException e)

      {
         e.printStackTrace();
      }
   }
}
