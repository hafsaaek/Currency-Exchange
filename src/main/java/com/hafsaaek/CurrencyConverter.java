package com.hafsaaek;

// import org.json.JSONObject;
import java.text.DecimalFormat;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.*;
import java.util.HashMap;
import java.util.Scanner;

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
            while (from < 11 || from > 5) {
                System.out.println(
                        "You have selected an in valid integer value, Please select an integer between 1 and 5 from the currencied below");
                System.out.println("1: GBP \t 2: USD \t 3: KSH \t 4: EUR \t 5: JPY");
                from = sc.nextInt();
            }
            fromCode = currencyCodeMap.get(from); // stores the reteived cureency code of the variable from

            System.out.println("What currency are you converting to? Please select an integer from the below");
            System.out.println("1: GBP \t 2: USD \t 3: KSH \t 4: EUR \t 5: JPY"); // \t introduces tab
            to = sc.nextInt();
            while (to < 11 || to > 5) {
                System.out.println(
                        "You have selected an in valid integer value, Please select an integer between 1 and 5 from the currencied below");
                System.out.println("1: GBP \t 2: USD \t 3: KSH \t 4: EUR \t 5: JPY");
                to = sc.nextInt();
            }
            toCode = currencyCodeMap.get(sc.nextInt()); // stores the reteived cureency code from scanner

            System.out.println("Please enter the amount you wish to convert");
            amount = sc.nextFloat();

            // Now, how do we get the exchange rate? Use an API that will provide this in
            // real time using HTTP
            sendHTTPGETRequest(fromCode, toCode, amount); // to be called later

            System.out.println("Would you like to continue and make another conversion?");
            System.out.println("1: Yes \t Any other integer: No");
            if (sc.nextInt() != 1) {
                programRunning = false;
            }

        } while (programRunning);
        System.out.println("Thank you for using the Currency COnverter program!"); // when the conversion has completed
    }

    public static void sendHTTPGETRequest(String fromCode, String toCode, double amount) {

        // Retrieve the API key from ApiTokenManager
        String apiKey = ApiTokenManager.getApiKey();

        // https://api.currencyapi.com/v3/convert?apikey=YOUR_API_KEY&base_currency=BASE_CURRENCY&target_currency=TARGET_CURRENCY&value=AMOUNT
        String getURL = "https://api.currencyapi.com/v3/latest?apikey=" + apiKey + "&base_currency=" + fromCode
                + "&target_currency=" + toCode + "&value=" + amount;
        // Test for selecing USD --> GBP
        System.out.println(getURL); // https://api.currencyapi.com/v3/latest?apikey=&base_currency=USD&target_currency=GBP&amount=20.0

        // try {
        // URI uri = new URI(getURL); // make the get URL an actual URI - URL is not
        // supported
        // HttpURLConnection httpURLConnection = (HttpURLConnection)
        // uri.toURL().openConnection(); // setup and open the HTTP connection
        // httpURLConnection.setRequestMethod("GET"); // set the request method as GET
        // int responsecode = httpURLConnection.getResponseCode();

        // if (responsecode == HttpURLConnection.HTTP_OK) {
        // BufferedReader in = new BufferedReader(new
        // InputStreamReader(httpURLConnection.getInputStream())); // if get reuqest is
        // successful, we want to read the response i.e. currency conversion

        // String inputLine;
        // StringBuffer response = new StringBuffer(); // a string that will parse the
        // repsonse form the request

        // // While there is stuff to read --> keep reading and add to string Buffer
        // while((inputLine = in.readLine())!= null) {
        // response.append(inputLine);

        // } in.close(); // close resource
        // // Testign the GET request, the repsonse is JSON - So we need to process this
        // into a java object using a 3rd party software

        // // Final output with all doubles to 2 decimal places
        // System.out.println(df.format(amount) + fromCode + " = " +
        // df.format((amount/exchangeRate)) + toCode);
        // } else {
        // System.out.println("Get request failed due to:" + responsecode);
        // }

        // } catch (URISyntaxException | IOException e) {
        // // TODO Auto-generated catch block
        // e.printStackTrace();
        // }

    }

}