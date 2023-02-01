package asteroids.nasa.asteroids.services.impl;

import asteroids.nasa.asteroids.services.AsteroidsService;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.reactive.function.client.WebClient;

@Service
public class AsteroidsServiceImpl implements AsteroidsService {

    private final  WebClient webClient;

    @Value("${asteroids.api-key}")
    private String apiKey ;

    public AsteroidsServiceImpl(WebClient webClient) {
        this.webClient = webClient;
    }

    @Override
    public String findAsteroids(String startDate, String endDate) {
        return webClient.get()
                .uri(uriBuilder -> uriBuilder
                        .path("neo/rest/v1/feed")
                        .queryParams(getHeaders(startDate,endDate))
                        .build())
                .exchange()
                .block()
                .bodyToMono(String.class)
                .block();
    }
    private MultiValueMap<String, String> getHeaders(String startDate, String endDate){
        MultiValueMap<String, String> map = new LinkedMultiValueMap<>();
        map.add("start_date", startDate);
        map.add("end_date", endDate);
        map.add("api_key", apiKey);

        return map;
    }
}
