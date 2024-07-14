package old;

// package com.hafsaaek.old;
import java.util.HashMap;
import java.util.Scanner;

public class oldCurrencyConverter {
    
    public static void main(String[] args) {
        // Define currency codes
        String[] currencyCodes = {"GBP", "KSH", "EGP", "USD", "EUR", "JPY", "SOS", "CNY", "SEK", "NZD"};

        for (String code: currencyCodes) {
            System.out.println(code.getClass());
            System.out.print(code + " ");
        }
        System.out.println("Welcome to the currency converter, below is a list of currencies this program can convert: " + currencyCodes);

        // Call createCurrencyMap method to print its contents 
        createCurrencyMap();

        // Create the vairable that will represent original and converted currency
        String fromCode, toCode; // Curency codes we want to convert frmo and to
        double amount; // amount we want to convert 

        // For input - we'll create a scanner: allows you to read input from various sources, such as the console (keyboard input), files, or strings and convert to the right 
        Scanner sc = new Scanner(System.in);
        System.out.println("Welcome to the currency converter ");
        
    }

    public static HashMap<Integer, String> createCurrencyMap()  {
        // Create a Hasmap to map currency codes to currency
        HashMap<Integer, String> currencyCodeMap = new HashMap<>();

        // Define currency codes
        String[] currencyCodes = {"GBP", "KSH", "EGP", "USD", "EUR", "JPY", "SOS", "CNY", "SEK", "NZD"};

        // Add currency codes to the HashMap with integers 1-10 as keys
        // Add currency codes:// currencyCodes.put(1,"GBP");
        
        // Add currency codes to the HashMap with integers 1-10 as keys using a for loop
        for (int i = 0; i < currencyCodes.length; i++) {
            currencyCodeMap.put(i + 1, currencyCodes[i]);
        }

         // Print the currency code map for testing
        //  for (HashMap.Entry<Integer, String> entry : currencyCodeMap.entrySet()) {
        //     System.out.println("Key: " + entry.getKey() + ", Value: " + entry.getValue());
        // } 
        return currencyCodeMap;

       

    }
    
}