package com.suvarna.aimultimedia.controller;

import com.suvarna.aimultimedia.dto.FileUploadResponseDto;
import com.suvarna.aimultimedia.entity.UploadedFile;
import com.suvarna.aimultimedia.service.FileService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/files")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class FileController {

    private final FileService fileService;

    @PostMapping("/upload")
    public FileUploadResponseDto uploadFile(
            @RequestParam("file") MultipartFile file) throws Exception {

        UploadedFile uploadedFile = fileService.uploadFile(file);

        return FileUploadResponseDto.builder()
                .id(uploadedFile.getId())
                .fileName(uploadedFile.getFileName())
                .fileType(uploadedFile.getFileType())
                .summary(uploadedFile.getSummary())
                .message("File uploaded and processed successfully")
                .build();
    }
}