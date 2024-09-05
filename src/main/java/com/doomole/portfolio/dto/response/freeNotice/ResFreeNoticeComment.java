package com.doomole.portfolio.dto.response.freeNotice;

import com.doomole.portfolio.dto.response.account.ResAccount;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ResFreeNoticeComment {
    private long commentIdx;
    private long freeNoticeIdx;
    private String content;
    private ResAccount resAccount;
    private int recommendCount;
    private String createDatetime;
    private String updateDatetime;
}
