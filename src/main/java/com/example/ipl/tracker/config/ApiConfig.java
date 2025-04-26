package com.example.ipl.tracker.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class ApiConfig {

    @Value("${cricbuzz.api.url}")
    private String apiUrl;
    
    @Value("${cricbuzz.api.key}")
    private String apiKey;
    
    @Value("${cricbuzz.api.host}")
    private String apiHost;
    
    @Bean
    public WebClient webClient() {
        return WebClient.builder()
                .baseUrl(apiUrl)
                .defaultHeader("X-RapidAPI-Key", apiKey)
                .defaultHeader("X-RapidAPI-Host", apiHost)
                .build();
    }
}
