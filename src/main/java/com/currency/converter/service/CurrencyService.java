package com.currency.converter.service;

import com.currency.converter.model.CustomConversionResponse;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.concurrent.CompletableFuture;

@Service
public class CurrencyService {

    private final RestTemplate restTemplate = new RestTemplate();
    @Value("${exchange.api.key}")
    private String API_KEY ; // ✅ your real key

    @Async
    public CompletableFuture<CustomConversionResponse> convert(String from, String to, double amount) {
        String url = String.format("https://v6.exchangerate-api.com/v6/%s/pair/%s/%s/%.2f", API_KEY, from, to, amount);
        String response = restTemplate.getForObject(url, String.class);
        JSONObject json = new JSONObject(response);

        if (!json.getString("result").equals("success")) {
            throw new RuntimeException("API call failed: " + json.getString("error-type"));
        }

        double rate = json.getDouble("conversion_rate");
        double result = rate * amount;
        CustomConversionResponse customResponse = new CustomConversionResponse(result, rate);

        return CompletableFuture.completedFuture(customResponse);
    }
}
