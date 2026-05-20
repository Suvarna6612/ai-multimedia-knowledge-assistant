package com.suvarna.aimultimedia.service;

import com.suvarna.aimultimedia.entity.UploadedFile;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface FileService {

    UploadedFile uploadFile(MultipartFile file) throws IOException;

    List<UploadedFile> getAllFiles();

    UploadedFile getFileById(Long id);
}