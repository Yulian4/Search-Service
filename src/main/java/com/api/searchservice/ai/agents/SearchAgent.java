package com.api.searchservice.ai.agents;

import java.util.List;


import dev.langchain4j.service.SystemMessage;
import dev.langchain4j.service.UserMessage;
import dev.langchain4j.service.V;
import dev.langchain4j.service.AiService;


//@AiService
public interface SearchAgent {

	@SystemMessage("""
	       Eres un asistente experto en búsqueda inteligente de recetas.
        Interpreta consultas en lenguaje natural y conviértelas en criterios
        estructurados: ingredientes, restricciones, dificultad, tiempo, categoría.
	    """)
	    @UserMessage("El usuario consulta: {{query}}")
	    List<String> procesarBusqueda(@V("query") String query);

	String interpret(String query);
}


