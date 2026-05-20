package com.suvarna.aimultimedia.service.impl;

import com.suvarna.aimultimedia.service.ChatService;
import lombok.RequiredArgsConstructor;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ChatServiceImpl implements ChatService {

    private final ChatClient.Builder chatClientBuilder;

    @Override
    public String askQuestion(String context, String question) {
        System.out.println("=== ChatService Started ===");
        System.out.println("Question: " + question);

        String prompt = """
        You are a helpful assistant.

        Answer the question only using the provided context.
        Keep the answer concise and factual.
        Do not make assumptions beyond the context.

        Context:
        %s

        Question:
        %s
        """.formatted(context, question);

        System.out.println("Calling Ollama...");

        String response = chatClientBuilder
                .build()
                .prompt()
                .user(prompt)
                .call()
                .content();

        System.out.println("Ollama Response: " + response);
        System.out.println("=== ChatService Finished ===");

        return response;
    }
}