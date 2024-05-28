package com.doomole.portfolio.dto.request.account;

import lombok.Data;

@Data
public class ReqPassword {
    private long accountIdx;
    private String prevPassword;
    private String nextPassword;
}
