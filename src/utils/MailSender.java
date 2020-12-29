/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

import java.util.Properties;
import javax.mail.Authenticator;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import model.MailAuth;

/**
 *
 * @author User
 */
public class MailSender {
    
   
    public static void sendMail(){
        String user= "";
        String passwrod = "s";
        MailAuth authenc = new MailAuth(user, passwrod);
        Session session = Session.getInstance(getSmtpProperties(), authenc);
    }
    
    public static Properties getSmtpProperties(){
        
        Properties prop = new Properties();
        prop.put("mail.smtp.host", "smtp.gmail.com");
        prop.put("mail.smtp.port", "587");
        prop.put("mail.smtp.auth", "true");
        prop.put("mail.smtp.starttls.enable", "true");
        
        return prop;
    }
    

    
}
