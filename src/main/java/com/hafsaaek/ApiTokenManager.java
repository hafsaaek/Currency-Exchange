package com.hafsaaek;

public class ApiTokenManager {

    // Define the system property key for the API token
    private static final String API_PROPERTY_KEY = "api.currency.token";

    // Retrieve the API token from the system property and store it as a constant
    private static final String LOADED_API_KEY = loadApiKey();

    // Method to load and validate the API token from system properties
    private static String loadApiKey() {
        String apiKey = System.getProperty(API_PROPERTY_KEY);
        if (apiKey == null || apiKey.isEmpty()) {
            throw new IllegalArgumentException("API token is missing or empty. Please set the API token.");
        }
        return apiKey;
    }

    // Public method to get the API token
    public static String getApiKey() {
        return LOADED_API_KEY;
    }
}
