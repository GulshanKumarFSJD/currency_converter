package com.currency.converter.controller;

import com.currency.converter.model.CustomConversionResponse;
import com.currency.converter.service.CurrencyService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.concurrent.CompletableFuture;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class CurrencyController {

    private final CurrencyService currencyService;
    @GetMapping("/convert")
    public CompletableFuture<CustomConversionResponse> convertCurrency(
            @RequestParam String from,
            @RequestParam String to,
            @RequestParam double amount) {

        return currencyService.convert(from, to, amount);
    }
}
