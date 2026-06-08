package com.suvarna.aimultimedia.service.impl;

import com.suvarna.aimultimedia.service.ChatService;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class ChatServiceImpl implements ChatService {

    @Value("${groq.api.key}")
    private String groqApiKey;

    private final String GROQ_URL = "https://api.groq.com/openai/v1/chat/completions";

    @Override
    public String askQuestion(String context, String question) {

        try {

            RestTemplate restTemplate = new RestTemplate();

            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            headers.setBearerAuth(groqApiKey);

            JSONObject body = new JSONObject();

            body.put("model", "llama-3.1-8b-instant");

            JSONArray messages = new JSONArray();

            JSONObject systemMessage = new JSONObject();
            systemMessage.put("role", "system");
            systemMessage.put("content",
                    "Answer questions only from the provided document content.");

            JSONObject userMessage = new JSONObject();
            userMessage.put("role", "user");
            userMessage.put("content",
                    "Document Content:\n" +
                            context.substring(0, Math.min(context.length(), 4000)) +
                            "\n\nQuestion:\n" + question);

            messages.put(systemMessage);
            messages.put(userMessage);

            body.put("messages", messages);

            HttpEntity<String> entity =
                    new HttpEntity<>(body.toString(), headers);

            ResponseEntity<String> response =
                    restTemplate.exchange(
                            GROQ_URL,
                            HttpMethod.POST,
                            entity,
                            String.class
                    );

            JSONObject jsonResponse =
                    new JSONObject(response.getBody());

            return jsonResponse
                    .getJSONArray("choices")
                    .getJSONObject(0)
                    .getJSONObject("message")
                    .getString("content");

        } catch (Exception e) {
            e.printStackTrace();
            return e.getMessage();
        }
    }
}