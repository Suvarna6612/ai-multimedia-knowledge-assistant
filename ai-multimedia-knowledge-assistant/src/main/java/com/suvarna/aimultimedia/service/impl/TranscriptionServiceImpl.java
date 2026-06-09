package com.suvarna.aimultimedia.service.impl;

import com.suvarna.aimultimedia.service.TranscriptionService;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;

@Service
public class TranscriptionServiceImpl implements TranscriptionService {

    @Override
    public String transcribe(File file) {

        try {

            ProcessBuilder processBuilder = new ProcessBuilder(
                    "whisper",
                    file.getAbsolutePath(),
                    "--model", "tiny",
                    "--language", "en",
                    "--output_format", "txt",
                    "--output_dir", file.getParent()
            );

            processBuilder.redirectErrorStream(true);

            Process process = processBuilder.start();

            try (java.io.BufferedReader reader =
                         new java.io.BufferedReader(
                                 new java.io.InputStreamReader(process.getInputStream()))) {

                String line;

                while ((line = reader.readLine()) != null) {
                    System.out.println("WHISPER: " + line);
                }
            }

            int exitCode = process.waitFor();

            if (exitCode != 0) {
                return "Failed to transcribe media.";
            }

            String fileName = file.getName();

            String baseName =
                    fileName.substring(0, fileName.lastIndexOf('.'));

            File transcriptFile =
                    new File(file.getParent(), baseName + ".txt");

            if (!transcriptFile.exists()) {
                return "Transcript not generated.";
            }

            return java.nio.file.Files.readString(
                    transcriptFile.toPath());

        } catch (Exception e) {

            e.printStackTrace();

            return "Media transcription failed.";
        }
    }
}