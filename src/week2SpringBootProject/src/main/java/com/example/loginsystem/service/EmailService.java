package com.example.loginsystem.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailService {

    private final JavaMailSender mailSender;
    
    @Value("${app.email.from}")
    private String fromEmail;
    
    @Value("${app.email.subject}")
    private String emailSubject;

    @Autowired
    public EmailService(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }

    /**
     * 发送注册成功邮件
     * @param toEmail 收件人邮箱
     * @param username 用户名
     * @param userId 用户ID
     */
    public void sendRegistrationEmail(String toEmail, String username, String userId) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(fromEmail);
        message.setTo(toEmail);
        message.setSubject(emailSubject);
        
        String text = "Dear " + username + ",\n\n" +
                "Congratulations! Your registration was successful.\n\n" +
                "Your account details:\n" +
                "Username: " + username + "\n" +
                "User ID: " + userId + "\n\n" +
                "Thank you for joining our system!\n\n" +
                "Best regards,\n" +
                "Login System Team";
        
        message.setText(text);
        mailSender.send(message);
    }
    
    /**
     * 发送注销成功邮件
     * @param toEmail 收件人邮箱
     * @param username 用户名
     * @param userId 用户ID
     */
    public void sendDeactivationEmail(String toEmail, String username, String userId) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(fromEmail);
        message.setTo(toEmail);
        message.setSubject("Account Deactivated");
        
        String text = "Dear " + username + ",\n\n" +
                "We regret to inform you that your account has been successfully deactivated.\n\n" +
                "Account details:\n" +
                "Username: " + username + "\n" +
                "User ID: " + userId + "\n\n" +
                "If this was not done by you, please contact us immediately.\n\n" +
                "Thank you for using our system.\n\n" +
                "Best regards,\n" +
                "Login System Team";
        
        message.setText(text);
        mailSender.send(message);
    }
}
