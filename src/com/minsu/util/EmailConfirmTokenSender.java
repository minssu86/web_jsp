package com.minsu.util;

import javax.mail.*;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

public class EmailConfirmTokenSender {
    private final String user = "bungle.master01@gmail.com";
    private final String password = "vslepxljztmvmfkv";


    private final Session session;


    public EmailConfirmTokenSender(){
        // SMTP 서버 정보 설정
        Properties properties= new Properties();
        properties.put("mail.smtp.auth", true);
        properties.put("mail.smtp.starttls.enable", true);
        properties.put("mail.smtp.host", "smtp.gmail.com");
        properties.put("mail.smtp.port", 587);
        properties.put("mail.smtp.ssl.trust", "smtp.gmail.com");
        properties.put("mail.smtp.ssl.protocols", "TLSv1.2");
        
        this.session = Session.getDefaultInstance(properties, new javax.mail.Authenticator(){
            @Override
           protected PasswordAuthentication getPasswordAuthentication(){
               return new PasswordAuthentication(user, password);
           }
        });

    }


    public void confirmTokenSend(String code, String email){
        try {
        	System.out.println("메일 전송 하기");
            MimeMessage message = new MimeMessage(session);

            message.setFrom(new InternetAddress(user));
            //수신자메일주소
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(email));
            // Subject
            message.setSubject("링크를 눌러 인증을 완료하세요"); //메일 제목을 입력
            // Text
            message.setText("http://localhost:8080/web_jsp/v1/user/checked-email?checkcode=" + code );    //메일 내용을 입력
            // send the message
            Transport.send(message); ////전송
            System.out.println("message sent successfully...");
        } catch (AddressException e) {
            e.printStackTrace();
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }

}
