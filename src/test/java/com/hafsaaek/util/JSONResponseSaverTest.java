// If you work directly with files, your unit tests become more complicated, as they need extra setup/teardown code to create and clean up the test directory, plus read/write files in each test, which slows the tests down.

package com.hafsaaek.util;

import static org.junit.Assert.assertEquals;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import org.junit.Test;

public class JSONResponseSaverTest {

    @Test
    public void writeResponseToFileTest() throws IOException{
        String apiResonse = "{\"data\":{\"value\":1.32}}";
        String tempFilePath = "src/test/resources/temp_response.json";

        JSONResponseSaver.setFilePath(tempFilePath);
        JSONResponseSaver.writeResponseToFile(apiResonse); // write api response to this temp file path

        // store the contents of the file
        String tempFileContent = Files.readString(Path.of(tempFilePath));

        assertEquals(apiResonse,tempFileContent);

        Files.deleteIfExists(Path.of(tempFilePath));

    }
    
}
