package Kavindu.company;


//import libraries
import com.sun.mail.util.MailConnectException;
import javax.mail.Message;
import javax.mail.*;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.mail.Session;
import javax.mail.Transport;
import java.util.Properties;


public class SendEmail{
    /* to create abstract class for send email */


    //attribute
    private String toEmail;
    private String content;
    private String subject;
    private String name;



    //email,password and user name
    private final String fromEmail = "Email";
    private final String passWord = "Password";
    private final String userName = "User name";


    //constructor
    public SendEmail(String toEmail, String subject, String content,String name) {
        this.toEmail = toEmail;
        this.subject = subject;
        this.content = content;
        this.name=name;

        createEmail();
    }

    private void createEmail(){
        /*to create methode for send email*/
        try {
            Properties properties = new Properties();
            properties.put("mail.smtp.auth", true);
            properties.put("mail.smtp.host", "smtp.gmail.com");
            properties.put("mail.smtp.port", 587);
            properties.put("mail.smtp.starttls.enable", true);
            properties.put("mail.trnsport.protocol", "smtp");

            Session session = Session.getInstance(properties, new Authenticator() {
                @Override
                protected PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication(fromEmail, passWord);
                }
            });
            MimeMessage message = new MimeMessage(session);

            //to enter subject of email
            message.setSubject(subject);

            //to enter content of email
            message.setText(content);

            //to get receiver email
            Address addressTo = new InternetAddress(toEmail);
            message.setRecipient(Message.RecipientType.TO, addressTo);

            //send email
            Transport.send(message);

            //to notify that the email was sent
            System.out.println("Sent email to "+name);


        }
        //catch exception
        catch (MailConnectException | AddressException error1){
            System.out.println("Connection issue");
            System.out.println("Email is not sent");
        } catch (MessagingException error2) {
            System.out.println(error2);
        }


    }


}

