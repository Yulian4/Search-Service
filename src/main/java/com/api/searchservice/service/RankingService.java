package com.api.searchservice.service;

import java.util.Comparator;
import java.util.List;

import org.springframework.stereotype.Service;

import com.api.searchservice.model.dto.RecipeDTO;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class RankingService {

    public List<RecipeDTO> rankRecipes(List<RecipeDTO> recipes, String interpretedQuery) {

        return recipes.stream()
                .sorted(Comparator.comparingInt(RecipeDTO::getPopularity).reversed())
                .toList();
    }
}
