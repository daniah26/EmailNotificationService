package com.example.demo.service;

import com.example.demo.dto.CreateNotificationRequest;
import com.example.demo.entity.Notification;
import com.example.demo.enums.NotificationChannel;
import com.example.demo.enums.NotificationStatus;
import com.example.demo.repository.NotificationRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class NotificationService {
    private final NotificationRepository notificationRepository;

    public NotificationService(NotificationRepository notificationRepository) {
        this.notificationRepository = notificationRepository;
    }

    public void createNotifications(CreateNotificationRequest request) {
        for (NotificationChannel channel : request.getChannels()) {
            Notification notification = new Notification();

            notification.setUserId(request.getUserId());
            notification.setType(request.getType());
            notification.setChannel(channel);
            notification.setRecipientEmail(request.getRecipientEmail());
            notification.setPushToken(request.getPushToken());
            notification.setSubject(request.getSubject());
            notification.setBody(request.getBody());
            notification.setTemplateCode(request.getTemplateCode());

            notification.setStatus(NotificationStatus.PENDING);
            notification.setRetryCount(0);
            notification.setNextAttemptAt(LocalDateTime.now());

            notificationRepository.save(notification);
        }
    }
}
