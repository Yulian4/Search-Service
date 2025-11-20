package com.api.searchservice.model.dto;

import java.util.List;

import lombok.Data;

@Data
public class SearchResponseDTO {
    private List<RecipeDTO> recipes;
    private String explanation; // texto breve con por qué se ordenaron / recomendaron
}
