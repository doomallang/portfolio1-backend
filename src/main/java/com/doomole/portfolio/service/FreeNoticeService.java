package com.doomole.portfolio.service;

import com.doomole.portfolio.converter.NoticeConverter;
import com.doomole.portfolio.dto.request.freeNotice.ReqFreeNotice;
import com.doomole.portfolio.dto.request.freeNotice.ReqFreeNoticeComment;
import com.doomole.portfolio.dto.response.common.ResCommonList;
import com.doomole.portfolio.dto.response.freeNotice.ResFreeNotice;
import com.doomole.portfolio.dto.response.freeNotice.ResFreeNoticeComment;
import com.doomole.portfolio.entity.account.Account;
import com.doomole.portfolio.entity.freeNotice.CommentRecommend;
import com.doomole.portfolio.entity.freeNotice.FreeNotice;
import com.doomole.portfolio.entity.freeNotice.FreeNoticeComment;
import com.doomole.portfolio.entity.freeNotice.NoticeRecommend;
import com.doomole.portfolio.repository.account.AccountRepository;
import com.doomole.portfolio.repository.freeNotice.CommentRecommendRepository;
import com.doomole.portfolio.repository.freeNotice.FreeNoticeCommentRepository;
import com.doomole.portfolio.repository.freeNotice.FreeNoticeRepository;
import com.doomole.portfolio.repository.freeNotice.NoticeRecommendRepository;
import com.doomole.portfolio.util.CommonUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class FreeNoticeService {
    private final FreeNoticeRepository freeNoticeRepository;
    private final FreeNoticeCommentRepository freeNoticeCommentRepository;
    private final AccountRepository accountRepository;
    private final CommentRecommendRepository commentRecommendRepository;
    private final NoticeRecommendRepository noticeRecommendRepository;

    public ResCommonList<List<ResFreeNotice>> getFreeNoticeList(int page, String sortType, String searchText) {
        Sort sort = getSort(sortType);
        Pageable pageable = PageRequest.of((page - 1), 10, sort);

        ResCommonList<List<ResFreeNotice>> list = selectFreeNoticeList(pageable, searchText);

        return list;
    }

    public ResCommonList<List<ResFreeNotice>> selectFreeNoticeList(Pageable pageable, String searchText) {
        long count = freeNoticeRepository.countAllByTitleContains(searchText);
        List<FreeNotice> freeNoticeList = freeNoticeRepository.findAllByTitleContains(searchText, pageable);

        List<ResFreeNotice> resFreeNoticeList = new ArrayList<>();
        for(FreeNotice freeNotice : freeNoticeList) {
            resFreeNoticeList.add(NoticeConverter.toResFreeNotice(freeNotice));
        }

        ResCommonList<List<ResFreeNotice>> list = new ResCommonList<>(count, resFreeNoticeList);

        return list;
    }

    public long addFreeNotice(ReqFreeNotice reqFreeNotice) {
        FreeNotice freeNotice = null;
        long freeNoticeIdx = reqFreeNotice.getFreeNoticeIdx();
        String title = reqFreeNotice.getTitle();
        String content = reqFreeNotice.getContent();

        // modify
        if(freeNoticeIdx != 0) {
            freeNotice = freeNoticeRepository.findById(freeNoticeIdx).get();
            freeNotice.setTitle(title);
            freeNotice.setContent(content);
            freeNotice.setUpdateDatetime(CommonUtil.getNowDate());
        // add
        } else {
            freeNotice = NoticeConverter.toFreeNotice(freeNoticeIdx, title, content);
        }

        freeNoticeRepository.save(freeNotice);

        return freeNotice.getFreeNoticeIdx();
    }

    public ResFreeNotice getFreeNotice(long freeNoticeIdx) {
        FreeNotice freeNotice = freeNoticeRepository.findById(freeNoticeIdx).get();
        int count = noticeRecommendRepository.countByFreeNoticeIdx(freeNoticeIdx);

        ResFreeNotice resFreeNotice = NoticeConverter.toResFreeNotice(freeNotice);
        resFreeNotice.setRecommendCount(count);
        return resFreeNotice;
    }

    public List<ResFreeNoticeComment> getFreeNoticeCommentList(long freeNoticeIdx) {
        List<ResFreeNoticeComment> list = new ArrayList<>();
        Optional<List<FreeNoticeComment>> freeNoticeCommentListOptional = freeNoticeCommentRepository.findByFreeNoticeIdx(freeNoticeIdx);

        if(freeNoticeCommentListOptional.isPresent()) {
            List<FreeNoticeComment> freeNoticeCommentList = freeNoticeCommentListOptional.get();
            for(FreeNoticeComment freeNoticeComment : freeNoticeCommentList) {
                long commentIdx = freeNoticeComment.getCommentIdx();
                int recommendCount = commentRecommendRepository.countByCommentIdx(commentIdx);
                ResFreeNoticeComment resFreeNoticeComment = NoticeConverter.toResFreeNoticeComment(freeNoticeComment);
                resFreeNoticeComment.setRecommendCount(recommendCount);

                list.add(resFreeNoticeComment);
            }
        }
        return list;
    }

    public void addFreeNoticeComment(ReqFreeNoticeComment reqFreeNoticeComment) {
        Account account = accountRepository.findByAccountId(reqFreeNoticeComment.getAccountId());

        FreeNoticeComment freeNoticeComment = NoticeConverter.toFreeNoticeComment(reqFreeNoticeComment);
        freeNoticeComment.setAccount(account);

        freeNoticeCommentRepository.save(freeNoticeComment);
    }

    public void modifyCommentRecommend(ReqFreeNoticeComment reqFreeNoticeComment) {
        long commentIdx = reqFreeNoticeComment.getCommentIdx();
        String accountId = reqFreeNoticeComment.getAccountId();
        Account account = accountRepository.findByAccountId(accountId);

        Optional<CommentRecommend> commentRecommendOptional = commentRecommendRepository.findByCommentIdxAndAccountIdx(commentIdx, account.getAccountIdx());
        if(commentRecommendOptional.isPresent()) {
            CommentRecommend commentRecommend = commentRecommendOptional.get();
            commentRecommendRepository.delete(commentRecommend);
        } else {
            CommentRecommend commentRecommend = CommentRecommend.builder()
                    .commentIdx(commentIdx)
                    .accountIdx(account.getAccountIdx())
                    .build();
            commentRecommendRepository.save(commentRecommend);
        }
    }

    public void modifyNoticeRecommend(ReqFreeNoticeComment reqFreeNoticeComment) {
        long freeNoticeIdx = reqFreeNoticeComment.getFreeNoticeIdx();
        String accountId = reqFreeNoticeComment.getAccountId();
        Account account = accountRepository.findByAccountId(accountId);

        Optional<NoticeRecommend> noticeRecommendOptional = noticeRecommendRepository.findByFreeNoticeIdxAndAccountIdx(freeNoticeIdx, account.getAccountIdx());
        if(noticeRecommendOptional.isPresent()) {
            NoticeRecommend noticeRecommend = noticeRecommendOptional.get();
            noticeRecommendRepository.delete(noticeRecommend);
        } else {
            NoticeRecommend noticeRecommend = NoticeRecommend.builder()
                    .freeNoticeIdx(freeNoticeIdx)
                    .accountIdx(account.getAccountIdx())
                    .build();
            noticeRecommendRepository.save(noticeRecommend);
        }
    }


    public Sort getSort(String sortType) {
        String type = "";
        if(sortType.equals("recent")) {
            type = "createDatetime";
        } else if(sortType.equals("view")) {
            type = "viewCount";
        } else if(sortType.equals("recommend")) {
            type = "recommendCount";
        }

        return Sort.by(Sort.Direction.DESC, type);
    }
}
