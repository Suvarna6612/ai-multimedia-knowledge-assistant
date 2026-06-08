package com.suvarna.aimultimedia.controller;

import com.suvarna.aimultimedia.dto.ApiResponseDto;
import com.suvarna.aimultimedia.dto.ChatRequestDto;
import com.suvarna.aimultimedia.dto.QuestionResponseDto;
import com.suvarna.aimultimedia.entity.UploadedFile;
import com.suvarna.aimultimedia.repository.UploadedFileRepository;
import com.suvarna.aimultimedia.service.ChatService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.suvarna.aimultimedia.service.TimestampService;

@RestController
@RequestMapping("/api/questions")
@CrossOrigin(origins = "*")
@RequiredArgsConstructor
public class QuestionController {

    private final ChatService chatService;
    private final UploadedFileRepository uploadedFileRepository;
    private final TimestampService timestampService;

    @PostMapping("/ask")
    public ResponseEntity<ApiResponseDto<QuestionResponseDto>> askQuestion(
            @RequestBody ChatRequestDto request) {

        System.out.println("QUESTION ENDPOINT HIT");

        // Get latest uploaded file
        UploadedFile latestFile = uploadedFileRepository
                .findTopByOrderByCreatedAtDesc()
                .orElseThrow(() ->
                        new RuntimeException("No uploaded file found"));

        // Use extracted text first, otherwise summary
        String context = latestFile.getExtractedText();

        if (context == null || context.isBlank()) {
            context = latestFile.getSummary();
        }

        if (context == null || context.isBlank()) {
            throw new RuntimeException("No extracted text or summary available.");
        }

        // Ask AI
        String answer = chatService.askQuestion(context, request.getQuestion());

        // Create response DTO
        Double timestamp =
                timestampService.findRelevantTimestamp(context, answer);

        QuestionResponseDto response =
                new QuestionResponseDto(
                        request.getQuestion(),
                        answer,
                        timestamp
                );

        // Return standardized API response
        return ResponseEntity.ok(
                ApiResponseDto.success(response)
        );
    }
}