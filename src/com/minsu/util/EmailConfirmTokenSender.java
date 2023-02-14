package com.minsu.util;

import javax.mail.*;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

public class EmailConfirmTokenSender {
    private final String user = "";
    private final String password = "";


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


    public void confirmTokenSend(){
        try {

//            MimeMessage mimeMessage = javaMailSender.createMimeMessage();
//            MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, "utf-8");
//            helper.setTo(receiverEmail); //받는사람
//            helper.setSubject("벙글! 회원가입 이메일 인증"); //메일제목
//            helper.setText(emailForm.getEmailBody(emailToken.getId()), true);
//
//            javaMailSender.send(mimeMessage)

            MimeMessage message = new MimeMessage(session);

            message.setFrom(new InternetAddress(user));
            //수신자메일주소
            message.addRecipient(Message.RecipientType.TO, new InternetAddress("cclbbl@nate.com"));
            // Subject
            message.setSubject("제목을 입력하세요"); //메일 제목을 입력
            // Text
            message.setText("내용을 입력하세요");    //메일 내용을 입력
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
