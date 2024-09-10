package com.hafsaaek;

import java.util.HashMap;
import java.util.Scanner;

import com.hafsaaek.service.ConverterService;


public class Main {

    public static void main(String[] args) {
        Boolean programRunning = true;

        ConverterService converterservice = new ConverterService();
        do {
            // Create a Hasmap to map currency codes to currency
            HashMap<Integer, String> currencyCodeMap = new HashMap<>();

            // Add currency codes to the HashMap with integers 1-10 as keys
            currencyCodeMap.put(1, "GBP");
            currencyCodeMap.put(2, "USD");
            currencyCodeMap.put(3, "KES");
            currencyCodeMap.put(4, "EUR");
            currencyCodeMap.put(5, "JPY");

            String fromCode, toCode; // Curency codes we want to convert from and to
            double amount; // amount we want to convert
            Integer from, to; // integer values from the scanner

            Scanner sc = new Scanner(System.in); // sc is an object initialized to read user input from console
            System.out.println("Welcome to the currency converter ");

            System.out.println("What currency are you converting from? Please select an integer from the below");
            System.out.println("1: GBP \t 2: USD \t 3: KES \t 4: EUR \t 5: JPY"); // \t introduces tab
            from = sc.nextInt(); // reads the next integer input from the user and fromcode is assigned to the
                                 // HashMap value associated with the integer input

            // create a while loop to re=promt users to enter an integer value between 1&5
            // for fromCode if it's not between 1 & 5
            while (from < 1 || from > 5) {
                System.out.println(
                        "You have selected an invalid integer value, Please select an integer between 1 and 5 from the currencies below");
                System.out.println("1: GBP \t 2: USD \t 3: KES \t 4: EUR \t 5: JPY");
                from = sc.nextInt();
            }
            fromCode = currencyCodeMap.get(from); // stores the reteived cureency code of the variable from, if from =1,
                                                  // fromCode=value where key=1

            System.out.println("What currency are you converting to? Please select an integer from the below");
            System.out.println("1: GBP \t 2: USD \t 3: KES \t 4: EUR \t 5: JPY"); // \t introduces tab
            to = sc.nextInt();
            while (to < 1 || to > 5) {
                System.out.println(
                        "You have selected an invalid integer value, Please select an integer between 1 and 5 from the currencied below");
                System.out.println("1: GBP \t 2: USD \t 3: KES \t 4: EUR \t 5: JPY");
                to = sc.nextInt();
            }
            toCode = currencyCodeMap.get(to); // stores the reteived cureency code from scanner

            System.out.println("Please enter the amount you wish to convert");
            amount = sc.nextFloat();

            converterservice.convert(fromCode, toCode, amount);

            System.out.println("Would you like to continue and make another conversion?");
            System.out.println("1: Yes \t Any other integer: No");
            if (sc.nextInt() != 1) {
                programRunning = false;
            }

        } while (programRunning);
        System.out.println("Thank you for using the Currency Converter program!"); // when the conversion has completed
    }
    
}
