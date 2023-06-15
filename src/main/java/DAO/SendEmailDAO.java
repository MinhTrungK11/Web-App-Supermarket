/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import org.apache.commons.lang3.RandomStringUtils;
import Entity.Account;
import jakarta.mail.Authenticator;
import jakarta.mail.Message;
import jakarta.mail.MessagingException;
import jakarta.mail.PasswordAuthentication;
import jakarta.mail.Session;
import jakarta.mail.Transport;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;
import java.util.Properties;
import java.util.Random;

/**
 *
 * @author nguye
 */
public class SendEmailDAO {

    public String getRandon() {
        Random rand = new Random();
        int number = rand.nextInt(999999);
        return String.format("%06d", number);
    }

    public boolean sendEmail(Account acc) {
        boolean test = false;

        final String username = "onlinestoreg11@gmail.com";
        final String password = "zwwtpabideppzvnp";
        String toEmail = acc.getEmail();

        Properties prop = new Properties();
        prop.put("mail.smtp.host", "smtp.gmail.com");
        prop.put("mail.smtp.port", "587");
        prop.put("mail.smtp.auth", "true");
        prop.put("mail.smtp.starttls.enable", "true"); //TLS

        Session session = Session.getInstance(prop,
                new Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, password);
            }
        });

        try {

            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress("onlinestoreg11@gmail.com"));
            message.setRecipients(
                    Message.RecipientType.TO,
                    InternetAddress.parse(toEmail)
            );
            message.setSubject("MÃ XÁC MINH G11 ONLINE STORE");
            message.setText("Xin chào " + acc.getName()
                    + "\n\n Mã xác minh của bạn là: " + acc.getCode());

            Transport.send(message);

            System.out.println("Done");
            test = true;

        } catch (MessagingException e) {
            e.printStackTrace();
        }

        return test;
    }

//    public static void main(String[] args) {
//
//        final String username = "onlinestoreg11@gmail.com";
//        final String password = "zwwtpabideppzvnp";
//
//        Properties prop = new Properties();
//		prop.put("mail.smtp.host", "smtp.gmail.com");
//        prop.put("mail.smtp.port", "587");
//        prop.put("mail.smtp.auth", "true");
//        prop.put("mail.smtp.starttls.enable", "true"); //TLS
//        
//        Session session = Session.getInstance(prop,
//                new Authenticator() {
//                    protected PasswordAuthentication getPasswordAuthentication() {
//                        return new PasswordAuthentication(username, password);
//                    }
//                });
//
//        try {
//
//            Message message = new MimeMessage(session);
//            message.setFrom(new InternetAddress("onlinestoreg11@gmail.com"));
//            message.setRecipients(
//                    Message.RecipientType.TO,
//                    InternetAddress.parse("nguyenthanhtuanmy1@gmail.com")
//            );
//            message.setSubject("Mã xác minh G11 ONLINE STORE");
//            message.setText("Xin chào,"
//                    + "\n\n Mã xác thực của bạn là: ");
//
//            Transport.send(message);
//
//            System.out.println("Done");
//
//        } catch (MessagingException e) {
//            e.printStackTrace();
//        }
//    }
}
