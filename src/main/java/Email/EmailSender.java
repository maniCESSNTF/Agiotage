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
