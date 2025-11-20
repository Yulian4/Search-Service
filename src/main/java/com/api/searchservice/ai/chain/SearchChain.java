package com.api.searchservice.ai.chain;

import com.api.searchservice.ai.agents.SearchAgent;

public class SearchChain {
	private final SearchAgent searchAgent;

    public SearchChain(SearchAgent searchAgent) {
        this.searchAgent = searchAgent;
    }

    public String runChain(String query) {
        // Aquí puedes añadir más etapas IA si quieres
        return searchAgent.interpret(query);
    }
}
