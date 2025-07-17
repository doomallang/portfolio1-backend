package com.doomole.portfolio.service;

import com.doomole.portfolio.dto.chat.ChatMessage;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

@Service
public class ChatMessagePublisher {
    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    @Autowired
    private ObjectMapper objectMapper; // ObjectMapper 추가

    private static final String CHAT_MESSAGES_KEY = "chatMessagesSet"; // Redis 키

    public void publish(ChatMessage message) {
        try {
            long timestamp = System.currentTimeMillis();
            message.setTimestamp(timestamp);
            // ChatMessage 객체를 JSON 문자열로 직렬화
            String messageJson = objectMapper.writeValueAsString(message);

            // Redis 채널에 메시지를 발행 (Pub/Sub)
            redisTemplate.convertAndSend("chat", messageJson);

            // Redis 리스트에 메시지를 저장
            // 각 메시지를 고유한 키로 저장 (예: UUID를 키로 사용)
            String messageKey = "chatMessage:" + UUID.randomUUID();

            redisTemplate.opsForValue().set(messageKey, messageJson);
            // TTL 설정 (예: 1시간 후 삭제)
            redisTemplate.expire(messageKey, 1, TimeUnit.HOURS);
            // Sorted Set에 메시지 키와 타임스탬프(score)를 저장
            redisTemplate.opsForZSet().add(CHAT_MESSAGES_KEY, messageKey, timestamp);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }

    public List<ChatMessage> getChatMessages() {
        // Sorted Set에서 모든 메시지 키 가져오기
        Set<Object> messageKeys = redisTemplate.opsForZSet().range(CHAT_MESSAGES_KEY, 0, -1);

        // 각 키에 해당하는 실제 메시지 가져오기
        List<ChatMessage> messages = new java.util.ArrayList<>();
        if (messageKeys != null) {
            for (Object key : messageKeys) {
                String messageJson = (String) redisTemplate.opsForValue().get(key.toString());
                if (messageJson != null) {
                    try {
                        // JSON을 ChatMessage 객체로 변환
                        ChatMessage message = objectMapper.readValue(messageJson, ChatMessage.class);
                        messages.add(message); // 변환된 메시지를 리스트에 추가
                    } catch (JsonMappingException e) {
                        e.printStackTrace();
                    } catch (JsonProcessingException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
        return messages; // 메시지를 반환
    }
}
