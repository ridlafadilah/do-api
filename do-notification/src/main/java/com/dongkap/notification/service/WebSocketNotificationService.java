package com.dongkap.notification.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

import com.dongkap.feign.dto.notification.BroadcastNotificationDto;

@Service
public class WebSocketNotificationService {

    @Autowired
    private SimpMessagingTemplate messagingTemplate;

    public void broadcast(String notif) {
    		BroadcastNotificationDto notification = new BroadcastNotificationDto();
    		notification.setContent(notif);
        this.messagingTemplate.convertAndSend("/public/broadcast", notification);
    }

}