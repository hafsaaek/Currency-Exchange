package com.hafsaaek.util;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class JSONResponseSaver {

    private static String filePath = "src/main/resources/response.json";

    // New method to write response to a JSON file
    public static void writeResponseToFile(String response) {
        // Define the path to the resources directory
        // filePath = "src/main/resources/response.json";

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            writer.write(response); // Write the response to the file
            System.out.println("Response written to: " + filePath); // Confirmation message
        } catch (IOException e) {
            System.err.println("Error writing to file: " + e.getMessage());
        }
    }

    // setter method to allow other instances and tests to alter the filepath
    public static void setFilePath(String newFilePath){
        filePath = newFilePath;
    }

}
