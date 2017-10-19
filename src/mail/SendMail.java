/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mail;

import java.util.Date;
import java.util.Properties;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.MimeMessage;
//import javax.mail.

/**
 *
 * @author User
 */
public class SendMail {
    private static String host ="send.one.com";
    public static void send(String to, String subject, String body) throws MessagingException{
        System.out.println("SendMail.send()");
        Properties props = new Properties();
        props.put("mail.smtp.host", host);
        Session session = Session.getInstance(props, null);
        MimeMessage msg = new MimeMessage(session);
        msg.setFrom("curt.furumark@curtfurumark.se");
        //msg.setRecipients(Message.RecipientType.TO, "curt.r.truc@gmail.com");
        msg.setRecipients(Message.RecipientType.TO, to);
        msg.setSubject(subject);
        msg.setSentDate(new Date());
        msg.setText(body);
        System.out.println("will try to send");
        Transport.send(msg, "curt.furumark@curtfurumark.se", "bettie12p");
        System.out.println("after send");
    }
    public static void main(String[] args){
    }
}
