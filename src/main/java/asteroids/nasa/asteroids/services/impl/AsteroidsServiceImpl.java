package asteroids.nasa.asteroids.services.impl;

import asteroids.nasa.asteroids.services.AsteroidsService;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

@Service
public class AsteroidsServiceImpl implements AsteroidsService {

    private final  WebClient webClient;

    @Value("${asteroids.api-key}")
    private final String apiKey = "AEIpNlPXeGs8Z0DUfmg9XU4eEjwFJ6KLzYwz58QV";

    public AsteroidsServiceImpl(WebClient webClient) {
        this.webClient = webClient;
    }

    @Override
    public String findAsteroids(String startDate, String endDate) {
        return webClient.get()
                .uri(uriBuilder -> uriBuilder
                        .path("neo/rest/v1/feed")
                        .queryParam("start_date", startDate)
                        .queryParam("end_date", endDate)
                        .queryParam("api_key", apiKey)
                        .build())
                .exchange()
                .block()
                .bodyToMono(String.class)
                .block();
    }
}
