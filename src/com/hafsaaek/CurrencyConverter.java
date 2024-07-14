package com.hafsaaek;

// package com.hafsaaek; // why is this erroring?
// setting the http request method to static beneifit 
// helper text in line 29?

import java.util.HashMap;
import java.util.Scanner;

public class CurrencyConverter {

    private static String API_Key = "";

    public String getAPI_Key() {
        return API_Key;
    }

    public void setAPI_Key(String API_Key) {
        // this.API_Key = API_Key; // this would be OK, if it was an instance variable but API Key is a class variable so it belongs to the class not an instance of the class
        CurrencyConverter.API_Key = API_Key; // class variable 
    }

    
    public static void main(String[] args) {
         // Create a Hasmap to map currency codes to currency
        HashMap<Integer, String> currencyCodeMap = new HashMap<>();

        // Add currency codes to the HashMap with integers 1-10 as keys
        currencyCodeMap.put(1,"GBP");
        currencyCodeMap.put(2,"USD");
        currencyCodeMap.put(3,"KSH");
        currencyCodeMap.put(4,"EUR");
        currencyCodeMap.put(5,"JPY");
        
        String fromCode, toCode; // Curency codes we want to convert frmo and to
        double amount; // amount we want to convert 

        Scanner sc = new Scanner(System.in); // sc is an object initialized to read user input from console
        System.out.println("Welcome to the currency converter ");

        System.out.println("What currency are you converting from? Please select an integer");
        System.out.println("1: GBP \t 2: USD \t 3: KSH \t 4: EUR \t 5: JPY"); // \t introduces tab
        fromCode = currencyCodeMap.get(sc.nextInt()); // stores the reteived cureency code from scanner
            // reads the next integer input from the user and fromcode is assigned to the HashMap value associated with the integer input

        System.out.println("What currency are you converting to? Please select an integer");
        System.out.println("1: GBP \t 2: USD \t 3: KSH \t 4: EUR \t 5: JPY"); // \t introduces tab
        toCode = currencyCodeMap.get(sc.nextInt()); // stores the reteived cureency code from scanner

        System.out.println("Please enter the amount you wish to convert");
        amount = sc.nextFloat();

        // Now, how do we get the exchange rate? Use an API that will provide this in real time using HTTP
        sendHTTPGETRequest(fromCode, toCode, amount); // to be called later
        
    }

    public static void sendHTTPGETRequest(String fromCode, String toCode, double amount) {
        // https://api.currencyapi.com/v3/convert?apikey=YOUR_API_KEY&base_currency=BASE_CURRENCY&target_currency=TARGET_CURRENCY&amount=AMOUNT
        String getURL = "https://api.currencyapi.com/v3/latest?apikey=" + API_Key + "&base_currency=" + fromCode+ "&target_currency=" + toCode +"&amount=" + amount;
        // Test for selecing USD --> GBP
        System.out.println(getURL); // https://api.currencyapi.com/v3/latest?apikey=&base_currency=USD&target_currency=GBP&amount=20.0

    }



}