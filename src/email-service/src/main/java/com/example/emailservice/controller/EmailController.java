package com.example.emailservice.controller;

import com.example.emailservice.model.EmailRequest;
import com.example.emailservice.service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/email")
public class EmailController {

    @Autowired
    private EmailService emailService;

    @PostMapping("/send")
    public ResponseEntity<?> sendEmail(@RequestBody EmailRequest emailRequest) {
        try {
            emailService.sendEmail(
                    emailRequest.getTo(),
                    emailRequest.getSubject(),
                    emailRequest.getText(),
                    emailRequest.getFrom()
            );
            return ResponseEntity.ok("邮件发送成功");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("邮件发送失败：" + e.getMessage());
        }
    }

    @PostMapping("/send-template/registration")
    public ResponseEntity<?> sendRegistrationEmail(@RequestBody EmailRequest emailRequest) {
        try {
            String text = "亲爱的用户，\n\n" +
                    "恭喜您注册成功！您现在可以使用我们的服务了。\n\n" +
                    "如果您有任何问题，请随时联系我们。\n\n" +
                    "祝好，\n" +
                    "系统管理员";

            emailService.sendEmail(
                    emailRequest.getTo(),
                    "注册成功 - 欢迎使用我们的服务",
                    text,
                    emailRequest.getFrom()
            );
            return ResponseEntity.ok("注册邮件发送成功");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("邮件发送失败：" + e.getMessage());
        }
    }

    @PostMapping("/send-template/notification")
    public ResponseEntity<?> sendNotificationEmail(@RequestBody EmailRequest emailRequest) {
        try {
            emailService.sendEmail(
                    emailRequest.getTo(),
                    emailRequest.getSubject() != null && !emailRequest.getSubject().isEmpty()
                            ? emailRequest.getSubject()
                            : "系统通知",
                    emailRequest.getText() != null && !emailRequest.getText().isEmpty()
                            ? emailRequest.getText()
                            : "您有一条新的系统通知",
                    emailRequest.getFrom()
            );
            return ResponseEntity.ok("通知邮件发送成功");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("邮件发送失败：" + e.getMessage());
        }
    }

    @RequestMapping("/health")
    public ResponseEntity<?> healthCheck() {
        return ResponseEntity.ok("Email Service is running");
    }
}