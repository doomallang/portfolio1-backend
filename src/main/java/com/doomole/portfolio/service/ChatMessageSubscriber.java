package com.doomole.portfolio.service;

import com.doomole.portfolio.dto.chat.ChatMessage;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

@Service
public class ChatMessageSubscriber {

    private final SimpMessagingTemplate messagingTemplate;
    private final ObjectMapper objectMapper;

    public ChatMessageSubscriber(SimpMessagingTemplate messagingTemplate, ObjectMapper objectMapper) {
        this.messagingTemplate = messagingTemplate;
        this.objectMapper = objectMapper;
    }

    public void receiveMessage(String message) {
        try {
            // 수신된 메시지를 ChatMessage 객체로 변환
            ChatMessage chatMessage = objectMapper.readValue(message, ChatMessage.class);
            // 받은 메시지 로직 처리
            System.out.println("Received message: " + chatMessage);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
