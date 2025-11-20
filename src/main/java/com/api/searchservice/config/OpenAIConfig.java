package com.api.searchservice.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import dev.langchain4j.model.chat.request.ResponseFormat;
import dev.langchain4j.model.openai.OpenAiChatModel;
import dev.langchain4j.model.chat.ChatModel;


@Configuration
public class OpenAIConfig {

	@Value("${openai.api-key}")
    private String openAiApiKey;

    @Value("${openai.model-name}")
    private String openAiModelName;

    @Value("${openai.temperature}")
    private Double openAiTemperature;

    @Value("${openai.log-requests}")
    private Boolean logRequests;

    @Bean
    @Primary
    public ChatModel openAiChatModel() {
        return OpenAiChatModel.builder()
                .apiKey(openAiApiKey)
                .modelName(openAiModelName)      // ej: "gpt-4.1", "gpt-4.1-mini"
                .responseFormat(ResponseFormat.JSON)
                .temperature(openAiTemperature)
                .logRequests(logRequests)
                .logResponses(logRequests)
                .build();
    }
}
