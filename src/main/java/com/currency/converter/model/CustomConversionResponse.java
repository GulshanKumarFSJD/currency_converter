package com.currency.converter.model;

import lombok.Data;

@Data
public class CustomConversionResponse {
    private double result;
    private double conversion_rate;

    public CustomConversionResponse(double result, double conversion_rate) {
        this.result = result;
        this.conversion_rate = conversion_rate;
    }

}
