package com.hafsaaek.util;


import org.json.JSONObject;

public class JSONParser {

    public static double getExchangeRate(String repsonse, String toCode) {
        try {
            JSONObject response_obj = new JSONObject(repsonse.toString()); // Directly create a JSONObject
            
            // Get the required data using its key
            double conversionRate = response_obj.getJSONObject("data").getJSONObject(toCode).getDouble("value");

            conversionRate = Math.round(conversionRate * 100.0) / 100.0;

            System.out.println("The latest conversion rate is: " + conversionRate);
            return conversionRate;
        } catch (Exception e) {
            System.out.println("JSON Parser method erroring due to: " + e.getMessage());
            return 0;
        }

    }
    
}