package com.doomole.portfolio.dto.request.account;

import lombok.Data;

@Data
public class ReqAccount {
    private Long accountIdx;
    private String accountId;
    private String password;
    private String name;
    private String nickname;
    private String email;
    private String avatar;
}
