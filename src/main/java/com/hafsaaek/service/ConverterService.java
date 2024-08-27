package com.hafsaaek.service;

import com.hafsaaek.api.APIService;
import com.hafsaaek.util.*;;


public class ConverterService {

    private APIService apiService; // to keep the APIService instance encapsulated within this class

    public ConverterService(){ // initalise the apiService field by creating a new instance of APIService class to allow this class to use the APIService to make HTTP GET requests to the currency API
        this.apiService = new APIService(null);
    }

    public void convert(String fromCode, String toCode, double amount){
        String response = apiService.sendHTTPGETRequest(fromCode, toCode);

        if (response != null){
            WriteResponseToFile.writeResponseToFile(response); // save the API response to a JSON file for later inspection

            double conversionRate = JSONParser.getExchangeRate(response, toCode); // extract the exchange rate from the JSON response

            double convertedAmount = amount * conversionRate;
            System.out.printf("%.2f %s = %.2f %s%n", amount, fromCode, convertedAmount, toCode);
        } else {
            System.out.println("Failed to retrieve conversion rate.");
        }

    }
    
    
}
