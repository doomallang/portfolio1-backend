package com.doomole.portfolio.dto.response.freeNotice;

import com.doomole.portfolio.dto.response.account.ResAccount;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ResFreeNotice {
    private long freeNoticeIdx;
    private String title;
    private String content;
    private String createDatetime;
    private String updateDatetime;
    private int viewCount;
    private int recommendCount;

    private ResAccount resAccount;
}
