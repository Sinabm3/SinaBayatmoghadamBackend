package com.sina.demo.backend.dto;

public record InfoCompassGameDto (Long id, String name, String horizontalAxisPositiveName,
                                 String horizontalAxisNegativeName,
                                 String verticalAxisPositiveName,
                                 String verticalAxisNegativeName) {
}
