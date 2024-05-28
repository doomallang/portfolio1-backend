package com.doomole.portfolio.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

@Service
@RequiredArgsConstructor
public class FileService {
    private final String fullPath = "/Users/aton/project/doomole/portfolio/frontend/public/saveImages/";

    public String saveFile(MultipartFile file) throws IOException {
        String fileName = System.currentTimeMillis() + file.getOriginalFilename();
        String savePath = fullPath + fileName;
        file.transferTo(new File(savePath));

        return fileName;
    }
}
