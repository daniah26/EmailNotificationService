package com.example.demo.channel;

import com.example.demo.entity.Notification;
import com.example.demo.enums.NotificationChannel;

public interface ChannelSender {
    NotificationChannel getSupportedChannel();
    void send(Notification notification);
}
