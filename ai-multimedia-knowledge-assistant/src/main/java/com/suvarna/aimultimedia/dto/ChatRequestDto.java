package com.suvarna.aimultimedia.dto;

import lombok.Data;

@Data
public class ChatRequestDto {
    private Long fileId;
    private String question;
}