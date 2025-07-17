package com.doomole.portfolio.controller;

import com.doomole.portfolio.dto.request.freeNotice.ReqFreeNotice;
import com.doomole.portfolio.dto.request.freeNotice.ReqFreeNoticeComment;
import com.doomole.portfolio.dto.response.common.ResCommonList;
import com.doomole.portfolio.dto.response.common.ResSuccess;
import com.doomole.portfolio.dto.response.freeNotice.ResFreeNotice;
import com.doomole.portfolio.dto.response.freeNotice.ResFreeNoticeComment;
import com.doomole.portfolio.exception.FailException;
import com.doomole.portfolio.service.FileService;
import com.doomole.portfolio.service.FreeNoticeService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/freeNotice")
public class FreeNoticeController {
    private final FreeNoticeService freeNoticeService;
    private final FileService fileService;

    /** 자유게시판 글목록 */
    @GetMapping("/list")
    public ResSuccess<ResCommonList<List<ResFreeNotice>>> getFreeNoticeList(
            @RequestParam(value="page") int page,
            @RequestParam(value="sortType") String sortType,
            @RequestParam(value="searchText") String searchText) {

        ResCommonList<List<ResFreeNotice>> list = freeNoticeService.getFreeNoticeList(page, sortType, searchText);

        return new ResSuccess<>(list);
    }

    @PostMapping("/image")
    public ResSuccess<String> addImage(@RequestParam(value = "image", required = false) MultipartFile image) {
        String saveFileName = "";
        if(!image.isEmpty()) {
            try {
                saveFileName = fileService.saveFile(image);
            } catch(Exception e) {
                throw new FailException(e.getMessage());
            }
        }

        return new ResSuccess<>(saveFileName);
    }

    @PostMapping("/write")
    public ResSuccess<String> addFreeNotice(@RequestBody ReqFreeNotice reqFreeNotice) {
        long freeNoticeIdx = freeNoticeService.addFreeNotice(reqFreeNotice);

        return new ResSuccess<>("" + freeNoticeIdx);
    }

    @GetMapping("/view")
    public ResSuccess<ResFreeNotice> getFreeNotice(@RequestParam(value="freeNoticeIdx") long freeNoticeIdx) {
        ResFreeNotice resFreeNotice = freeNoticeService.getFreeNotice(freeNoticeIdx);

        return new ResSuccess<>(resFreeNotice);
    }

    @GetMapping("/comment")
    public ResSuccess<List<ResFreeNoticeComment>> getFreeNoticeComment(@RequestParam(value="freeNoticeIdx") long freeNoticeIdx) {
        List<ResFreeNoticeComment> list = freeNoticeService.getFreeNoticeCommentList(freeNoticeIdx);

        return new ResSuccess<>(list);
    }

    @PostMapping("/comment")
    public ResSuccess<String> addFreeNoticeComment(@RequestBody ReqFreeNoticeComment reqFreeNoticeComment) {
        freeNoticeService.addFreeNoticeComment(reqFreeNoticeComment);

        return new ResSuccess<>("success");
    }

    @PutMapping("/comment/recommend")
    public ResSuccess<String> modifyCommentRecommend(@RequestBody ReqFreeNoticeComment reqFreeNoticeComment) {
        freeNoticeService.modifyCommentRecommend(reqFreeNoticeComment);

        return new ResSuccess<>("success");
    }

    @PutMapping("/recommend")
    public ResSuccess<String> modifyNoticeRecommend(@RequestBody ReqFreeNoticeComment reqFreeNoticeComment) {
        freeNoticeService.modifyNoticeRecommend(reqFreeNoticeComment);

        return new ResSuccess<>("success");
    }
}
