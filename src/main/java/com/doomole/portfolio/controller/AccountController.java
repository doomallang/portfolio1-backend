package com.doomole.portfolio.controller;

import com.doomole.portfolio.dto.request.account.ReqAccount;
import com.doomole.portfolio.dto.request.account.ReqPassword;
import com.doomole.portfolio.dto.response.account.ResAccount;
import com.doomole.portfolio.dto.response.common.ResSuccess;
import com.doomole.portfolio.dto.response.common.ResToken;
import com.doomole.portfolio.service.AccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/account")
public class AccountController {
    private final AccountService accountService;

    /** 로그인 */
    @PostMapping("/login")
    public ResSuccess<ResToken> login(@RequestBody ReqAccount reqAccount) {
        ResToken resToken = accountService.login(reqAccount);
        System.out.println(resToken);
        return new ResSuccess<>(resToken);
    }

    /** 가입 */
    @PostMapping("/join")
    public ResSuccess<Long> join(@RequestBody ReqAccount reqAccount) {
        long accountIdx = accountService.join(reqAccount);

        return new ResSuccess<>(accountIdx);
    }

    /** 정보 */
    @GetMapping("/info")
    public ResSuccess<ResAccount> getInfo(@RequestParam(value="accountId") String accountId) {
        ResAccount resAccount = accountService.getInfo(accountId);

        return new ResSuccess<>(resAccount);
    }

    /** 비밀번호 변경 */
    @PostMapping("/passwordChange")
    public ResSuccess<String> passwordChange(@RequestBody ReqPassword reqPassword) {
        accountService.passwordChange(reqPassword);

        return new ResSuccess<>("success");
    }

    /** 수정 */
    @PostMapping("/modify")
    public ResSuccess<String> modify(@RequestBody ReqAccount reqAccount) {
        accountService.modify(reqAccount);

        return new ResSuccess<>("success");
    }
}
