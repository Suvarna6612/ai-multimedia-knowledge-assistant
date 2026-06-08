package com.suvarna.aimultimedia.service.impl;

import com.suvarna.aimultimedia.service.SummaryService;
import org.springframework.stereotype.Service;

@Service
public class SummaryServiceImpl implements SummaryService {

    @Override
    public String generateSummary(String text) {

        if (text == null || text.isBlank()) {
            return "No content available for summary.";
        }

        // Remove extra spaces/newlines
        text = text.replaceAll("\\s+", " ").trim();

        // Allow larger PDFs but limit size safely
        int maxLength = Math.min(text.length(), 5000);

        return text.substring(0, maxLength) + "...";
    }
}