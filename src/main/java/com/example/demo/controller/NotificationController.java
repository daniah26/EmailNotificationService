package com.example.demo.controller;

import com.example.demo.dto.CreateNotificationRequest;
import com.example.demo.service.NotificationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/notifications")
public class NotificationController {

    private final NotificationService notificationService;

    public NotificationController(NotificationService notificationService) {
        this.notificationService = notificationService;
    }

    @PostMapping
    public ResponseEntity<String> createNotification(@RequestBody CreateNotificationRequest request) {
        notificationService.createNotifications(request);
        return ResponseEntity.ok("Notifications created successfully");
    }
}
