package com.suvarna.aimultimedia.service;

import org.springframework.web.multipart.MultipartFile;
import java.io.File;

public interface PdfService {

    String extractText(File file);

    String summarizePdf(MultipartFile file);
}