package com.suvarna.aimultimedia.controller;

import com.suvarna.aimultimedia.service.AiService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class AiTestController {

    private final AiService aiService;

    @GetMapping("/api/test-ai")
    public String testAi(
            @RequestParam(defaultValue = "Java is a programming language.") String context,
            @RequestParam(defaultValue = "What is Java?") String question) {

        return aiService.askQuestion(context, question);
    }

    @GetMapping("/api/test-summary")
    public String testSummary(
            @RequestParam(defaultValue = "Spring Boot simplifies Java backend development.") String content) {

        return aiService.generateSummary(content);
    }
}