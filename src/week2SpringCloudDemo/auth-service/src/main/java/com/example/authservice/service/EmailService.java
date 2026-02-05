package com.example.authservice.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailService {

    @Autowired
    private JavaMailSender mailSender;

    public void sendRegistrationSuccessEmail(String toEmail, String username) {
        try {
            SimpleMailMessage message = new SimpleMailMessage();
            message.setFrom("xxxxxx");
            message.setTo(toEmail);
            message.setSubject("注册成功 - 欢迎使用我们的服务");
            message.setText("亲爱的 " + username + "，\n\n" +
                    "恭喜您注册成功！您现在可以使用我们的服务了。\n\n" +
                    "如果您有任何问题，请随时联系我们。\n\n" +
                    "祝好，\n" +
                    "系统管理员");

            mailSender.send(message);
            System.out.println("邮件发送成功：" + toEmail);
        } catch (Exception e) {
            System.err.println("邮件发送失败：" + e.getMessage());
            // 即使邮件发送失败，也不影响注册流程
        }
    }
}