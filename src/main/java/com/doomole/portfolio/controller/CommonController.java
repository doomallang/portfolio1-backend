package com.doomole.portfolio.controller;

import com.doomole.portfolio.dto.response.common.ResAuth;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestClient;

@RestController
@RequiredArgsConstructor
@RequestMapping("/common")
public class CommonController {

    @Value("${gpt.key}")
    private String chatKey;

    /** 토큰 검증 */
    @GetMapping("/validToken")
    public ResAuth validToken() {
        ResAuth resAuth = new ResAuth(200, "success");

        return resAuth;
    }

    @GetMapping("/chat")
    public void chat() {
        RestClient restClient = RestClient.create();
        ResponseEntity response = restClient.post()
                .uri("https://api.openai.com/v1/models")
                .header("Authorization", "Bearer " + chatKey)
                .retrieve()
                .body(ResponseEntity.class);

        System.out.println(response);
    }
}
