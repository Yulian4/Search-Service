package com.api.searchservice.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.api.searchservice.model.dto.RecipeDTO;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class IntegrationService {

    private final RestTemplate restTemplate;

    public List<RecipeDTO> getRecipeDetails(List<RecipeDTO> filtered) {

        return filtered.stream()
                .map(recipe -> restTemplate.getForObject(
                        "http://recipe-service/api/recipes/" + recipe.getId(),
                        RecipeDTO.class
                ))
                .toList();
    }
}

