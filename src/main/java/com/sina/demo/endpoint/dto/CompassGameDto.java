package com.sina.demo.endpoint.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.util.Set;

public record CompassGameDto(
        Long id,

        @NotBlank
        @Size(max = 100, message = "Name cannot exceed 100 characters")
        String name,

        @NotBlank
        @Size(max = 100, message = "Horizontal axis positive name cannot exceed 100 characters")
        String horizontalAxisPositiveName,

        @NotBlank
        @Size(max = 100, message = "Horizontal axis negative name cannot exceed 100 characters")
        String horizontalAxisNegativeName,

        @NotBlank
        @Size(max = 100, message = "Vertical axis positive name cannot exceed 100 characters")
        String verticalAxisPositiveName,

        @NotBlank
        @Size(max = 100, message = "Vertical axis negative name cannot exceed 100 characters")
        String verticalAxisNegativeName,

        @NotBlank
        @Size(max = 100, message = "Password cannot exceed 100 characters")
        String password,

        @NotNull(message = "questionDtos cannot be null")
        @Size(min = 1, message = "questionDtos cannot be empty")
        Set<QuestionDto> questionDtos
) {}
