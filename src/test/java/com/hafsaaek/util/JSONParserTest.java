package com.hafsaaek.util;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class JSONParserTest {

    @Test
    public void retrieveExchangeRate() {
        String sampleResponse = "{"
                + "\"data\": {"
                + "   \"USD\": {\"value\": 1.319104379}}"
                + "  }"
                + "}";
        double delta = 0.01;
        assertEquals(1.32, JsonParser.getExchangeRate(sampleResponse, "USD"), delta);

    }

    @Test
    public void retrieveExchangeRate_EmptyJSON() {
        String emptyRepsonse = "{"
        + "\"data\": {"
        + "   \"GBP\": {\"value\": null}}" 
        + "}";
        double delta = 0.01; // GBP has no value

        assertEquals(0.00, JsonParser.getExchangeRate(emptyRepsonse, "GBP"), delta);
    }

    @Test
    public void retrieveExchangeRate_ZeroValue() {
        String emptyRepsonse = "{"
                + "\"data\": {"
                + "   \"GBP\": {\"value\": 0.0}}"
                + "  }"
                + "}";
        double delta = 0.01;

        assertEquals(0.00, JsonParser.getExchangeRate(emptyRepsonse, "GBP"), delta);
    }

    @Test
    public void retrieveExchangeRate_NegativeValues() {
        String negativeResponse = "{ \"data\": { \"USD\": {\"value\": -1.32} } }";
        double delta = 0.01;

        assertEquals(-1.32, JsonParser.getExchangeRate(negativeResponse, "USD"), delta);
    }

    @Test
    public void retrieveExchangeRate_MalformedJSON() {
        String malformedResponse = "{ \"data\": { \"USD\": {\"value\": 1.32 ";
        assertEquals(0.0, JsonParser.getExchangeRate(malformedResponse, "USD"), 0.01);
    }

}
