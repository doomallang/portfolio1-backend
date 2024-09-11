package com.doomole.portfolio.controller;

import com.doomole.portfolio.dto.chat.ChatMessage;
import com.doomole.portfolio.dto.response.common.ResSuccess;
import com.doomole.portfolio.service.ChatMessagePublisher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Set;

@RestController
@RequestMapping
public class ChatController {
    @Autowired
    private ChatMessagePublisher messagePublisher;

    @MessageMapping("/chat.sendMessage")
    @SendTo("/topic/messages")
    public ChatMessage sendMessage(@Payload ChatMessage chatMessage) {
        // 메시지를 Redis에 저장
        messagePublisher.publish(chatMessage);

        // 채팅 메시지 처리 로직
        return chatMessage;
    }

    // Redis에서 모든 채팅 메시지를 가져오는 API
    @GetMapping("/api/chat/messages")
    public ResSuccess<Set<String>> getChatMessages() {
        Set<String> messageKeys = messagePublisher.getChatMessages();

        return new ResSuccess<>(messageKeys);
    }
}
