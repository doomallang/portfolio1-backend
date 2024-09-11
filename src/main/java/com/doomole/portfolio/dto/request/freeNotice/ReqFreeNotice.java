package com.doomole.portfolio.dto.request.freeNotice;

import lombok.Data;

@Data
public class ReqFreeNotice {
    private long freeNoticeIdx;
    private String title;
    private String content;
    private String createDatetime;
    private String updateDatetime;
    private int viewCount;
    private int recommendCount;

    private String accountId;
}
