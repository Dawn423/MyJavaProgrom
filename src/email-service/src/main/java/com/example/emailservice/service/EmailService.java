package com.example.emailservice.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailService {

    @Autowired
    private JavaMailSender mailSender;

    public void sendEmail(String to, String subject, String text, String from) {
        try {
            SimpleMailMessage message = new SimpleMailMessage();
            message.setTo(to);
            message.setSubject(subject);
            message.setText(text);
            if (from != null && !from.isEmpty()) {
                message.setFrom(from);
            }
            mailSender.send(message);
            System.out.println("邮件发送成功：" + to);
        } catch (Exception e) {
            System.err.println("邮件发送失败：" + e.getMessage());
            throw e;
        }
    }
}