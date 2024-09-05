package com.doomole.portfolio.converter;

import com.doomole.portfolio.dto.request.freeNotice.ReqFreeNoticeComment;
import com.doomole.portfolio.dto.response.account.ResAccount;
import com.doomole.portfolio.dto.response.freeNotice.ResFreeNotice;
import com.doomole.portfolio.dto.response.freeNotice.ResFreeNoticeComment;
import com.doomole.portfolio.entity.freeNotice.FreeNotice;
import com.doomole.portfolio.entity.freeNotice.FreeNoticeComment;
import com.doomole.portfolio.util.CommonUtil;

public class NoticeConverter {
    public static ResFreeNotice toResFreeNotice(FreeNotice freeNotice) {
        ResAccount resAccount = AccountConverter.toResAccount(freeNotice.getAccount());
        return ResFreeNotice.builder()
                .freeNoticeIdx(freeNotice.getFreeNoticeIdx())
                .title(freeNotice.getTitle())
                .content(freeNotice.getContent())
                .createDatetime(freeNotice.getCreateDatetime())
                .updateDatetime(freeNotice.getUpdateDatetime())
                .viewCount(freeNotice.getViewCount())
                .resAccount(resAccount)
                .build();
    }

    public static FreeNotice toFreeNotice(long freeNoticeIdx, String title, String content) {
        return FreeNotice.builder()
                .freeNoticeIdx(freeNoticeIdx)
                .title(title)
                .content(content)
                .createDatetime(CommonUtil.getNowDate())
                .updateDatetime(CommonUtil.getNowDate())
                .build();
    }

    public static ResFreeNoticeComment toResFreeNoticeComment(FreeNoticeComment freeNoticeComment) {
        ResAccount resAccount = AccountConverter.toResAccount(freeNoticeComment.getAccount());
        return ResFreeNoticeComment.builder()
                .commentIdx(freeNoticeComment.getCommentIdx())
                .freeNoticeIdx(freeNoticeComment.getFreeNoticeIdx())
                .content(freeNoticeComment.getContent())
                .resAccount(resAccount)
                .createDatetime(CommonUtil.localDatetimeToString(freeNoticeComment.getCreateDatetime()))
                .updateDatetime(CommonUtil.localDatetimeToString(freeNoticeComment.getUpdateDatetime()))
                .build();
    }

    public static FreeNoticeComment toFreeNoticeComment(ReqFreeNoticeComment reqFreeNoticeComment) {

        return FreeNoticeComment.builder()
                .commentIdx(reqFreeNoticeComment.getCommentIdx() == null ? 0 : reqFreeNoticeComment.getCommentIdx())
                .freeNoticeIdx(reqFreeNoticeComment.getFreeNoticeIdx() == null ? 0 : reqFreeNoticeComment.getFreeNoticeIdx())
                .content(reqFreeNoticeComment.getContent())
                .build();
    }
}
