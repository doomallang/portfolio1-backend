package com.doomole.portfolio.controller;

import com.doomole.portfolio.dto.request.freeNotice.ReqFreeNotice;
import com.doomole.portfolio.dto.response.common.ResCommonList;
import com.doomole.portfolio.dto.response.common.ResSuccess;
import com.doomole.portfolio.dto.response.freeNotice.ResFreeNotice;
import com.doomole.portfolio.exception.FailException;
import com.doomole.portfolio.service.FileService;
import com.doomole.portfolio.service.FreeNoticeService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

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
}
