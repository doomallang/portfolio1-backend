package com.doomole.portfolio.dto.request.freeNotice;

import com.doomole.portfolio.dto.response.account.ResAccount;
import lombok.Data;

@Data
public class ReqFreeNoticeComment {
    private Long commentIdx;
    private Long freeNoticeIdx;
    private String content;
    private ResAccount resAccount;
    private Integer recommendCount;
    private String createDatetime;
    private String updateDatetime;

    private String accountId;
}
