package com.api.searchservice.ai.agents;

import com.api.searchservice.model.dto.SearchResponseDTO;

import dev.langchain4j.service.SystemMessage;
import dev.langchain4j.service.UserMessage;
import dev.langchain4j.service.V;



public interface SearchAgent {
	@SystemMessage("""

			Eres un asistente que ayuda a buscar recetas personalizadas.
			      El usuario puede pedir recetas por ingredientes, tiempo, dificultad,
			      o tipo de comida. Debes llamar a las herramientas cuando sea necesario.
			""")
	@UserMessage("{{input}}")
	SearchResponseDTO buscarRecetas(@V("input") String consulta);
}
