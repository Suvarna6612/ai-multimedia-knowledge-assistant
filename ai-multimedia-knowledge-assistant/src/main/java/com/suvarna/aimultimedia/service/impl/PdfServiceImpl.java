package com.suvarna.aimultimedia.service.impl;

import com.suvarna.aimultimedia.service.ChatService;
import com.suvarna.aimultimedia.service.PdfService;
import lombok.RequiredArgsConstructor;
import org.apache.pdfbox.Loader;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import java.io.File;
import java.io.IOException;

@Service
@RequiredArgsConstructor
public class PdfServiceImpl implements PdfService {

    private final ChatService chatService;

    @Override
    public String extractText(File file) {
        try (PDDocument document = Loader.loadPDF(file)) {
            PDFTextStripper stripper = new PDFTextStripper();
            return stripper.getText(document);
        } catch (IOException e) {
            throw new RuntimeException("Failed to extract text from PDF", e);
        }
    }

    @Override
    public String summarizePdf(MultipartFile file) {
        String text = extractText(convertToFile(file));
        return chatService.askQuestion(
                text,
                "Summarize this PDF in concise bullet points"
        );
    }

    private File convertToFile(MultipartFile multipartFile) {
        try {
            File tempFile = File.createTempFile("upload-", multipartFile.getOriginalFilename());
            multipartFile.transferTo(tempFile);
            return tempFile;
        } catch (IOException e) {
            throw new RuntimeException("Failed to convert MultipartFile to File", e);
        }
    }
}