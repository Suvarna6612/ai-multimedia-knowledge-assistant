package com.suvarna.aimultimedia;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@SpringBootApplication
@EntityScan(basePackages = "com.suvarna.aimultimedia.entity")
public class AiMultimediaKnowledgeAssistantApplication {

    public static void main(String[] args) {
        SpringApplication.run(AiMultimediaKnowledgeAssistantApplication.class, args);
    }
}