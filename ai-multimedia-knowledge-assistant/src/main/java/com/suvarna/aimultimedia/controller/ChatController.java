package com.suvarna.aimultimedia.controller;

import com.suvarna.aimultimedia.dto.QuestionRequestDto;
import com.suvarna.aimultimedia.dto.QuestionResponseDto;
import com.suvarna.aimultimedia.entity.UploadedFile;
import com.suvarna.aimultimedia.repository.UploadedFileRepository;
import com.suvarna.aimultimedia.service.AiService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/chat")
@CrossOrigin(origins = "*")
public class ChatController {

    private final AiService aiService;
    private final UploadedFileRepository uploadedFileRepository;

    public ChatController(AiService aiService,
                          UploadedFileRepository uploadedFileRepository) {
        this.aiService = aiService;
        this.uploadedFileRepository = uploadedFileRepository;
    }

    @PostMapping("/{fileId}")
    public QuestionResponseDto askQuestion(
            @PathVariable Long fileId,
            @Valid @RequestBody QuestionRequestDto request) {

        UploadedFile uploadedFile = uploadedFileRepository.findById(fileId)
                .orElseThrow(() ->
                        new RuntimeException("File not found with id: " + fileId));

        String documentContent = uploadedFile.getExtractedText();

        if (documentContent == null || documentContent.isBlank()) {
            documentContent = uploadedFile.getSummary();
        }

        String answer = aiService.askQuestion(documentContent, request.getQuestion());

        return QuestionResponseDto.builder()
                .question(request.getQuestion())
                .answer(answer)
                .build();

    }
}