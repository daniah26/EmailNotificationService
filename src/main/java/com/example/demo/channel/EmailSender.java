package com.example.demo.channel;

import com.example.demo.entity.Notification;
import com.example.demo.enums.NotificationChannel;
import com.example.demo.service.EmailService; 
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class EmailSender implements ChannelSender {

    private final EmailService emailService;

    public EmailSender(EmailService emailService) {
        this.emailService = emailService;
    }

    @Override
    public NotificationChannel getSupportedChannel() {
        return NotificationChannel.EMAIL;
    }

    @Override
    public void send(Notification notification) {
        try {
            if (notification.getTemplateCode() != null) {
                // Route to template email
                Map<String, Object> vars = Map.of(
                        "subject", notification.getSubject(),
                        "body", notification.getBody(),
                        "userId", notification.getUserId() != null ? notification.getUserId() : ""
                );
                emailService.sendTemplateEmail(
                        notification.getRecipientEmail(),
                        notification.getSubject(),
                        notification.getTemplateCode(),
                        vars
                );
            } else {
                // Route to simple email
                emailService.sendSimpleEmail(
                        notification.getRecipientEmail(),
                        notification.getSubject(),
                        notification.getBody()
                );
            }
        } catch (Exception e) {
            throw new RuntimeException("Failed to send email", e);
        }
    }
}