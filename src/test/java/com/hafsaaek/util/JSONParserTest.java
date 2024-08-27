package com.hafsaaek.util;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class JSONParserTest {

    @Test
    public void retrieveExchangeRate(){
        // create a simple json parser object to allow us to call the method on

        String sampleResponse = "{"
        + "\"data\": {"
        + "   \"USD\": {\"value\": 1.32}"
        + "  }"
        + "}";
        double delta = 0.01;
        assertEquals(1.32,JSONParser.getExchangeRate(sampleResponse, "USD"), delta);

    }
    
}
