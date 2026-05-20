package com.suvarna.aimultimedia.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ApiResponseDto<T> {

    private boolean success;
    private T data;
    private String message;

    public static <T> ApiResponseDto<T> success(T data) {
        return new ApiResponseDto<>(true, data, "Success");
    }

    public static <T> ApiResponseDto<T> failure(String message) {
        return new ApiResponseDto<>(false, null, message);
    }
}