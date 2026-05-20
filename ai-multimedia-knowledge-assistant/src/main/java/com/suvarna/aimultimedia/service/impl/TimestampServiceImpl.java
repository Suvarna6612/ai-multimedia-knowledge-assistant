package com.suvarna.aimultimedia.service.impl;

import com.suvarna.aimultimedia.service.TimestampService;
import org.springframework.stereotype.Service;

@Service
public class TimestampServiceImpl implements TimestampService {

    @Override
    public Double findRelevantTimestamp(String transcriptJson, String answer) {

        // If transcript or answer is empty, return 0 seconds
        if (transcriptJson == null || transcriptJson.isBlank()
                || answer == null || answer.isBlank()) {
            return 0.0;
        }

        // Convert both to lowercase for case-insensitive matching
        String transcript = transcriptJson.toLowerCase();
        String aiAnswer = answer.toLowerCase();

        // Split answer into words
        String[] words = aiAnswer.split("\\s+");

        // Find the first meaningful word that appears in transcript
        for (String word : words) {

            // Ignore very short/common words
            if (word.length() < 4) {
                continue;
            }

            // If transcript contains this word,
            // return a sample timestamp based on its position
            int index = transcript.indexOf(word);

            if (index != -1) {
                // Convert character position into approximate seconds
                // Assumption: ~15 characters spoken per second
                return index / 15.0;
            }
        }

        // If nothing matches, return 0
        return 0.0;
    }
}