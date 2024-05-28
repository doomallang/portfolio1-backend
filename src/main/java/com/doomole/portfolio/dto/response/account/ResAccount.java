package com.doomole.portfolio.dto.response.account;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ResAccount {
    private long accountIdx;
    private String accountId;
    private String password;
    private String name;
    private String nickname;
    private String email;
    private String avatar;
}
