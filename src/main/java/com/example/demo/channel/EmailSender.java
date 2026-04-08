package com.example.demo.channel;

import com.example.demo.entity.Notification;
import com.example.demo.enums.NotificationChannel;

public class EmailSender implements ChannelSender{
    @Override
    public NotificationChannel getSupportedChannel() {
        return NotificationChannel.EMAIL;
    }

    @Override
    public void send(Notification notification) {
        System.out.println("Sending EMAIL to: " + notification.getRecipientEmail());
        System.out.println("Subject: " + notification.getSubject());
        System.out.println("Body: " + notification.getBody());
    }
}
