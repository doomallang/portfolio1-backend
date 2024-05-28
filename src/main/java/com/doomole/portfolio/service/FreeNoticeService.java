package com.doomole.portfolio.service;

import com.doomole.portfolio.dto.request.freeNotice.ReqFreeNotice;
import com.doomole.portfolio.dto.response.common.ResCommonList;
import com.doomole.portfolio.dto.response.freeNotice.ResFreeNotice;
import com.doomole.portfolio.entity.freeNotice.FreeNotice;
import com.doomole.portfolio.repository.freeNotice.FreeNoticeRepository;
import com.doomole.portfolio.util.CommonUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class FreeNoticeService {
    private final FreeNoticeRepository freeNoticeRepository;

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
            resFreeNoticeList.add(toResFreeNotice(freeNotice));
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
            freeNotice = toFreeNotice(freeNoticeIdx, title, content);
        }

        freeNoticeRepository.save(freeNotice);

        return freeNotice.getFreeNoticeIdx();
    }

    public ResFreeNotice getFreeNotice(long freeNoticeIdx) {
        FreeNotice freeNotice = freeNoticeRepository.findById(freeNoticeIdx).get();
        ResFreeNotice resFreeNotice = toResFreeNotice(freeNotice);

        return resFreeNotice;
    }

    public ResFreeNotice toResFreeNotice(FreeNotice freeNotice) {
        return ResFreeNotice.builder()
                .freeNoticeIdx(freeNotice.getFreeNoticeIdx())
                .title(freeNotice.getTitle())
                .content(freeNotice.getContent())
                .createDatetime(freeNotice.getCreateDatetime())
                .updateDatetime(freeNotice.getUpdateDatetime())
                .viewCount(freeNotice.getViewCount())
                .recommendCount(freeNotice.getRecommendCount())
                .build();
    }

    public FreeNotice toFreeNotice(long freeNoticeIdx, String title, String content) {
        return FreeNotice.builder()
                .freeNoticeIdx(freeNoticeIdx)
                .title(title)
                .content(content)
                .createDatetime(CommonUtil.getNowDate())
                .updateDatetime(CommonUtil.getNowDate())
                .build();
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
