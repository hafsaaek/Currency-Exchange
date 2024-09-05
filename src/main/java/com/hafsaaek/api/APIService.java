package com.hafsaaek.api;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.*;

public class APIService {

    public ApiTokenManager apiTokenManager;

    public APIService(ApiTokenManager apiTokenManager) {
        this.apiTokenManager = apiTokenManager;
    }
    
    public String sendHTTPGETRequest(String fromCode, String toCode) {

        // Retrieve the API key from ApiTokenManager
        String apiKey = ApiTokenManager.getApiKey();

        String getURL = "https://api.currencyapi.com/v3/latest?apikey=" + apiKey + "&base_currency=" + fromCode + "&currencies=" + toCode;

        System.out.println(getURL); 

        try {
            URI uri = new URI(getURL); // converts the string getURL into a URI object - URL is not supported
            HttpURLConnection httpURLConnection = (HttpURLConnection) // open a connection to the API server 
            uri.toURL().openConnection(); // opens a connection to the specified URL by converting the URI to a URL object and creates an HTTP connection
            httpURLConnection.setRequestMethod("GET"); // set the request method as GET
            int responsecode = httpURLConnection.getResponseCode(); // save response code

            // Check the response and read it if it HTTP OK i.e. 200 repsonse code
            if (responsecode == HttpURLConnection.HTTP_OK) {
                BufferedReader in = new BufferedReader(new InputStreamReader(httpURLConnection.getInputStream()));
                String inputLine; // declare a variable to temporarily store each line of text read from the server's response
                StringBuffer response = new StringBuffer(); // a string that will parse the repsonse from the server

                // While there is stuff to read --> keep reading and add to string Buffer
                while ((inputLine = in.readLine()) != null) {
                    response.append(inputLine);
                }
                in.close(); // close resource

                return response.toString();

            } else { // if the repsonse is not "200"
                System.out.println("Get request failed due to:" + responsecode);
                return null;
            }

        } catch (URISyntaxException | IOException e) {
            e.printStackTrace(); // log the exception for debugging
            return null;
        }

    }
    
}
