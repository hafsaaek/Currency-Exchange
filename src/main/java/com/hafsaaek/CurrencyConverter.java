package com.hafsaaek;

// import org.json.JSONObject;
import java.text.DecimalFormat;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.*;
import java.util.HashMap;
import java.util.Scanner;
import org.json.*;


public class CurrencyConverter {
    private static final DecimalFormat df = new DecimalFormat("0.00");

    @SuppressWarnings("resource")
    public static void main(String[] args) {
        Boolean programRunning = true;
        do {
            // Create a Hasmap to map currency codes to currency
            HashMap<Integer, String> currencyCodeMap = new HashMap<>();

            // Add currency codes to the HashMap with integers 1-10 as keys
            currencyCodeMap.put(1, "GBP");
            currencyCodeMap.put(2, "USD");
            currencyCodeMap.put(3, "KSH");
            currencyCodeMap.put(4, "EUR");
            currencyCodeMap.put(5, "JPY");

            String fromCode, toCode; // Curency codes we want to convert from and to
            double amount; // amount we want to convert
            Integer from, to; // integer values from the scanner

            Scanner sc = new Scanner(System.in); // sc is an object initialized to read user input from console
            System.out.println("Welcome to the currency converter ");

            System.out.println("What currency are you converting from? Please select an integer from the below");
            System.out.println("1: GBP \t 2: USD \t 3: KSH \t 4: EUR \t 5: JPY"); // \t introduces tab
            from = sc.nextInt(); // reads the next integer input from the user and fromcode is assigned to the
                                 // HashMap value associated with the integer input

            // create a while loop to re=promt users to enter an integer value between 1&5
            // for fromCode if it's not between 1 & 5
            while (from < 1 || from > 5) {
                System.out.println(
                        "You have selected an invalid integer value, Please select an integer between 1 and 5 from the currencies below");
                System.out.println("1: GBP \t 2: USD \t 3: KSH \t 4: EUR \t 5: JPY");
                from = sc.nextInt();
            }
            fromCode = currencyCodeMap.get(from); // stores the reteived cureency code of the variable from

            System.out.println("What currency are you converting to? Please select an integer from the below");
            System.out.println("1: GBP \t 2: USD \t 3: KSH \t 4: EUR \t 5: JPY"); // \t introduces tab
            to = sc.nextInt();
            while (to < 1 || to > 5) {
                System.out.println(
                        "You have selected an invalid integer value, Please select an integer between 1 and 5 from the currencied below");
                System.out.println("1: GBP \t 2: USD \t 3: KSH \t 4: EUR \t 5: JPY");
                to = sc.nextInt();
            }
            toCode = currencyCodeMap.get(to); // stores the reteived cureency code from scanner

            System.out.println("Please enter the amount you wish to convert");
            amount = sc.nextFloat();
            // System.out.println("Debug: Reached this point in the code.");

            // Now, how do we get the exchange rate? Use an API that will provide this in
            // real time using HTTP
            sendHTTPGETRequest(fromCode, toCode, amount); // to be called later
            // System.out.println("Debug: Reached this point in the code.");

            System.out.println("Would you like to continue and make another conversion?");
            System.out.println("1: Yes \t Any other integer: No");
            if (sc.nextInt() != 1) {
                programRunning = false;
            }

        } while (programRunning);
        System.out.println("Thank you for using the Currency Converter program!"); // when the conversion has completed
    }

    public static void sendHTTPGETRequest(String fromCode, String toCode, double amount) {

        // Retrieve the API key from ApiTokenManager
        String apiKey = ApiTokenManager.getApiKey();

        // https://api.currencyapi.com/v3/convert?apikey=YOUR_API_KEY&base_currency=BASE_CURRENCY&target_currency=TARGET_CURRENCY&value=AMOUNT
        String getURL = "https://api.currencyapi.com/v3/latest?apikey=" + apiKey + "&base_currency=" + fromCode
                + "&target_currency=" + toCode + "&value=" + amount;
        // Test for selecing USD --> GBP
        System.out.println(getURL); // https://api.currencyapi.com/v3/latest?apikey=<APIKEY>&base_currency=GBP&target_currency=USD&value=20.0

        try {
            URI uri = new URI(getURL); // converts the string getURL into a URI object - URL is not supported
            HttpURLConnection httpURLConnection = (HttpURLConnection) // make an instance of the httpconnection &
                                                                      // prepares to cast the connection opened from the
                                                                      // URI to an HttpURLConnection
            uri.toURL().openConnection(); // opens a connection to the specified URL by converting the URI to a URL
                                          // object and creates an HTTP connection
            httpURLConnection.setRequestMethod("GET"); // set the request method as GET
            int responsecode = httpURLConnection.getResponseCode(); // retrieves the response code from the server

            if (responsecode == HttpURLConnection.HTTP_OK) {
                BufferedReader in = new BufferedReader(new InputStreamReader(httpURLConnection.getInputStream()));
                String inputLine; // declare a variable to temporarily store each line of text read from the
                                  // server's response
                StringBuffer response = new StringBuffer(); // a string that will parse the repsonse from the server

                // While there is stuff to read --> keep reading and add to string Buffer
                while ((inputLine = in.readLine()) != null) {
                    response.append(inputLine);
                }
                in.close(); // close resource

                System.out.println(response.toString()); // print the json reponse
                // Store the response in a response.json file
                writeResponseToFile(response.toString());

                // // Final output with all doubles to 2 decimal places
                // System.out.println(df.format(amount) + fromCode + " = " +
                // df.format((amount/exchangeRate)) + toCode);

                // call the parser method
                parseJSONReponse(response.toString());

            } else {
                System.out.println("Get request failed due to:" + responsecode);
            }

        } catch (URISyntaxException | IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }

    // New method to write response to a JSON file
    private static void writeResponseToFile(String response) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("response.json"))) {
            writer.write(response); // Write the response to the file
            System.out.println("Response written to response.json"); // Confirmation message
        } catch (IOException e) {
            System.err.println("Error writing to file: " + e.getMessage());
        }
    }

    public static void parseJSONReponse(String repsonse) {
        try {
            JSONObject response_obj = new JSONObject(repsonse.toString()); // Directly create a JSONObject
            JSONObject data_obj = (JSONObject) response_obj.get("data"); 

            // Get the required data using its key
            System.out.println(data_obj.get("GBP"));
        } catch (Exception e) {
            System.out.println("JSON Parser method erroring due to: " + e.getMessage());
        }

    }

}
