package com.api.searchservice.service;

import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.api.searchservice.ai.chain.SearchChain;
import com.api.searchservice.model.dto.RecipeDTO;
import com.api.searchservice.model.dto.SearchRequestDTO;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class SearchService {

    private final SearchChain searchChain;
    private final RestTemplate restTemplate;

    private final String RECIPE_SERVICE_URL = "http://recipe-service/api/recipes/search";

    public String interpretQuery(SearchRequestDTO request) {
        String interpreted = searchChain.runChain(request.getQuery());
        log.info("🧠 Interpretación IA: {}", interpreted);
        return interpreted;
    }

    public List<RecipeDTO> searchCandidates(String interpretedJson) {
        // Se envía al Recipe Service directamente
        RecipeDTO[] result =
                restTemplate.postForObject(RECIPE_SERVICE_URL, interpretedJson, RecipeDTO[].class);

        return Arrays.asList(result);
    }
}

