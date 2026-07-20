package com.yourapp.entertainmenthub.config;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
@EnableConfigurationProperties(ExternalApiProperties.class)
public class WebClientConfig {

    @Bean
    public WebClient tmdbWebClient(ExternalApiProperties props) {
        return WebClient.builder()
                .baseUrl(props.tmdb().baseUrl())
                .defaultHeader("Authorization", "Bearer " + props.tmdb().apiKey())
                .build();
    }

    @Bean
    public WebClient watchmodeWebClient(ExternalApiProperties props) {
        return WebClient.builder()
                .baseUrl(props.watchmode().baseUrl())
                .build();
    }

    @Bean
    public WebClient rawgWebClient(ExternalApiProperties props) {
        return WebClient.builder()
                .baseUrl(props.rawg().baseUrl())
                .build();
    }
}
