package com.suvarna.aimultimedia.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class OllamaServiceImpl {

    private final RestTemplate restTemplate = new RestTemplate();

    @Value("${ollama.base-url}")
    private String ollamaBaseUrl;

    @Value("${ollama.model}")
    private String model;

    public String askQuestion(String context, String question) {

        String prompt = """
                Answer the question based on the context below.

                Context:
                %s

                Question:
                %s
                """.formatted(context, question);

        Map<String, Object> request = new HashMap<>();
        request.put("model", model);
        request.put("prompt", prompt);
        request.put("stream", false);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<Map<String, Object>> entity =
                new HttpEntity<>(request, headers);

        ResponseEntity<Map> response = restTemplate.exchange(
                ollamaBaseUrl + "/api/generate",
                HttpMethod.POST,
                entity,
                Map.class
        );

        return response.getBody().get("response").toString();
    }
}