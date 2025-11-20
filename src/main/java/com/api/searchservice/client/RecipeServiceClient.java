package com.api.searchservice.client;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.api.searchservice.model.dto.RecipeDTO;

@FeignClient(name = "recipe-service", url = "${recipe.service.url:http://localhost:8083}")
public interface RecipeServiceClient {

    @GetMapping("/api/v1/recipes/search-by-ingredients")
    List<RecipeDTO> searchByIngredients(@RequestParam("q") String query,
                                        @RequestParam(value = "limit", required = false) Integer limit);
}