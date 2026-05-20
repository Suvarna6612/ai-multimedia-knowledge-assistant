package com.suvarna.aimultimedia.service.impl;

import com.suvarna.aimultimedia.service.AiService;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.stereotype.Service;

@Service
public class OpenAiServiceImpl implements AiService {

    private final ChatClient chatClient;

    public OpenAiServiceImpl(ChatClient.Builder builder) {
        this.chatClient = builder.build();
    }

    @Override
    public String askQuestion(String context, String question) {
        String prompt = """
                You are an AI assistant.
                Answer ONLY using the provided context.

                Context:
                %s

                Question:
                %s
                """.formatted(context, question);

        return chatClient.prompt()
                .user(prompt)
                .call()
                .content();
    }

    @Override
    public String generateSummary(String content) {
        String prompt = """
                Summarize the following content in concise bullet points:

                %s
                """.formatted(content);

        return chatClient.prompt()
                .user(prompt)
                .call()
                .content();
    }
}