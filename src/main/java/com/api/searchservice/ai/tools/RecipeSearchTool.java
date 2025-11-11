package com.api.searchservice.ai.tools;

import java.util.List;

import com.api.searchservice.client.RecipeServiceClient;
import com.api.searchservice.model.dto.RecipeDTO;

import dev.langchain4j.agent.tool.Tool;


public class RecipeSearchTool {
	private final RecipeServiceClient recipeClient;
	
	public RecipeSearchTool(RecipeServiceClient recipeClient) {
		this.recipeClient = recipeClient;

	}
	
	
	@Tool(name = "bucarRecetas")
	public List<RecipeDTO> buscarRecetas(String ingredientes){
		return recipeClient.findByIngredients(ingredientes);
	}
}
