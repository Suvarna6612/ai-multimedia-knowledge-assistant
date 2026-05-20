package com.suvarna.aimultimedia.service;

public interface AiService {
    String askQuestion(String context, String question);
    String generateSummary(String content);
}