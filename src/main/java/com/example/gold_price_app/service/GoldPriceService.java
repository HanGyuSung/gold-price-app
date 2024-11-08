// src/main/java/com/example/goldpriceapp/service/GoldPriceService.java
package com.example.gold_price_app.service;

import com.example.gold_price_app.model.GoldPriceResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class GoldPriceService {

    @Value("${goldapi.key}")
    private String apiKey;

    private final String apiUrl = "https://www.goldapi.io/api/XAU/USD";
    private final RestTemplate restTemplate = new RestTemplate();

    public GoldPriceResponse getGoldPrice() {
        HttpHeaders headers = new HttpHeaders();
        headers.set("x-access-token", apiKey);
        headers.set("Content-Type", "application/json");

        HttpEntity<String> entity = new HttpEntity<>(headers);

        ResponseEntity<GoldPriceResponse> response = restTemplate.exchange(
                apiUrl, HttpMethod.GET, entity, GoldPriceResponse.class);

        return response.getBody();
    }

}
