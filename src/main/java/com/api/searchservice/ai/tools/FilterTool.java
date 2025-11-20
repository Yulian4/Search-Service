package com.api.searchservice.ai.tools;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import dev.langchain4j.agent.tool.Tool;

@Component
public class FilterTool {

    @Tool("Filtra recetas eliminando aquellas que contengan ingredientes no permitidos.")
    public List<String> filtrarPorRestricciones(List<String> recetas, List<String> ingredientesProhibidos) {
        return recetas.stream()
                .filter(r -> ingredientesProhibidos.stream().noneMatch(r::contains))
                .collect(Collectors.toList());
    }
}