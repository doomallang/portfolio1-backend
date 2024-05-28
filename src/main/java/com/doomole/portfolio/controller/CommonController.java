package com.doomole.portfolio.controller;

import com.doomole.portfolio.dto.response.common.ResAuth;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/common")
public class CommonController {

    /** 토큰 검증 */
    @GetMapping("/validToken")
    public ResAuth validToken() {
        ResAuth resAuth = new ResAuth(200, "success");

        return resAuth;
    }
}
