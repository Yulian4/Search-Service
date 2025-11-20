package com.api.searchservice.model.dto;

import java.util.List;

public record RecipeDTO(
        Long id,
        String name,
        String description,
        List<String> ingredients,
        String difficulty,
        Integer timeMinutes,
        List<String> tags
) {}