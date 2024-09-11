package com.doomole.portfolio.dto.chat;

import lombok.Data;

@Data
public class ChatMessage {
    private String sender;
    private String content;
    private String timestamp;
}
