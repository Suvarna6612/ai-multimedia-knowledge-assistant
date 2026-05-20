package com.suvarna.aimultimedia.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class FileUploadResponseDto {

    private Long id;
    private String fileName;
    private String fileType;
    private String summary;
    private String message;
}