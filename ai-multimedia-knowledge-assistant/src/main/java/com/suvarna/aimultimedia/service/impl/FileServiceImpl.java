package com.suvarna.aimultimedia.service.impl;

import com.suvarna.aimultimedia.entity.FileType;
import com.suvarna.aimultimedia.entity.UploadedFile;
import com.suvarna.aimultimedia.repository.UploadedFileRepository;
import com.suvarna.aimultimedia.service.FileService;
import com.suvarna.aimultimedia.service.PdfService;
import com.suvarna.aimultimedia.service.SummaryService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import com.suvarna.aimultimedia.service.TranscriptionService;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
public class FileServiceImpl implements FileService {

    private final UploadedFileRepository uploadedFileRepository;
    private final PdfService pdfService;
    private final SummaryService summaryService;
    private final TranscriptionService transcriptionService;

    @Value("${app.upload-dir:uploads}")
    private String uploadDir;

    public FileServiceImpl(
            UploadedFileRepository uploadedFileRepository,
            PdfService pdfService,
            SummaryService summaryService,
            TranscriptionService transcriptionService) {
        this.uploadedFileRepository = uploadedFileRepository;
        this.pdfService = pdfService;
        this.summaryService = summaryService;
        this.transcriptionService = transcriptionService;
    }

    @Override
    public UploadedFile uploadFile(MultipartFile file) throws IOException {

        Files.createDirectories(Paths.get(uploadDir));

        String originalName = file.getOriginalFilename();
        String extension = getExtension(originalName);
        FileType fileType = determineFileType(extension);

        String storedFileName = UUID.randomUUID() + "_" + originalName;
        Path targetPath = Paths.get(uploadDir, storedFileName);

        Files.copy(file.getInputStream(), targetPath);

        String extractedText = null;

        if (fileType == FileType.PDF) {
            extractedText = pdfService.extractText(targetPath.toFile());
        } else if (fileType == FileType.AUDIO || fileType == FileType.VIDEO) {
            extractedText = transcriptionService.transcribe(targetPath.toFile());
        }

        String summary = summaryService.generateSummary(extractedText);

        UploadedFile uploadedFile = UploadedFile.builder()
                .fileName(originalName)
                .fileType(fileType.name())
                .storagePath(targetPath.toString())
                .extractedText(extractedText)
                .summary(summary)
                .createdAt(LocalDateTime.now())
                .build();

        return uploadedFileRepository.save(uploadedFile);
    }

    @Override
    public List<UploadedFile> getAllFiles() {
        return uploadedFileRepository.findAll();
    }

    @Override
    public UploadedFile getFileById(Long id) {
        return uploadedFileRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("File not found with id: " + id));
    }

    private String getExtension(String fileName) {
        if (fileName == null || !fileName.contains(".")) {
            return "";
        }
        return fileName.substring(fileName.lastIndexOf('.') + 1).toLowerCase();
    }

    private FileType determineFileType(String extension) {
        extension = extension.toLowerCase();

        if (extension.equals("pdf")) {
            return FileType.PDF;
        }

        if (extension.equals("mp3") ||
                extension.equals("wav") ||
                extension.equals("mpeg") ||
                extension.equals("m4a") ||
                extension.equals("aac")) {
            return FileType.AUDIO;
        }

        if (extension.equals("mp4") ||
                extension.equals("mov") ||
                extension.equals("avi") ||
                extension.equals("mkv")) {
            return FileType.VIDEO;
        }

        throw new RuntimeException("Unsupported file type: " + extension);
    }
}