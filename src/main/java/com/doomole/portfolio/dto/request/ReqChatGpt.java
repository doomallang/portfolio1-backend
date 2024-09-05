package com.doomole.portfolio.dto.request;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ReqChatGpt {
    private String model;

    private String prompt;

    private float temperature;
}
