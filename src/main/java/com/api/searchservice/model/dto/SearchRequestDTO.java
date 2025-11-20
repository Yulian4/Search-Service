package com.api.searchservice.model.dto;

import java.util.List;

public record SearchRequestDTO(
        String query,                     // “quiero una receta con pollo”
        List<String> ingredients,         // ["pollo", "arroz"]
        Integer maxTimeMinutes,           // 30
        String difficulty,                // "Fácil"
        Boolean useAi                     // true = usar IA (SearchAgent)
) {}
