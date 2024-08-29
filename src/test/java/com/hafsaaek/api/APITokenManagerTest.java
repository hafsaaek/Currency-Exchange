package com.hafsaaek.api;

import static org.junit.Assert.assertEquals;

import org.junit.Rule;
import org.junit.Test;
import org.junit.contrib.java.lang.system.EnvironmentVariables;
import com.github.stefanbirkner.systemlambda.SystemLambda; 

public class APITokenManagerTest {

    @Test
    public void testSytemPropertyValidity() {

        System.setProperty("api.currency.token", "test-api-token-from-system-property"); // set a mock system property

        assertEquals("test-api-token-from-system-property", ApiTokenManager.getApiKey());

        System.clearProperty("api.currency.token"); // clear the system property
    }

    // Java does not allow doesn't allow setting environment variables directly through a specific class or exisiting method - we will need to create a mock environment of this using a library such as System Lambda or System Rules. We will test using both

    @Rule
    public final EnvironmentVariables envVariables = new EnvironmentVariables();
    @Test
    public void testEnvVariableValiditySR() { // Using System Rules to test to rerieve api token from env variable

        // ensure system propery is cleared before retreiving env variable
        System.clearProperty("api.currency.token");

        
        envVariables.set("API_CURRENCY_TOKEN", "test-api-token-from-env-variable");

        String apiKey = ApiTokenManager.getApiKey();

        assertEquals("test-api-token-from-env-variable", apiKey);
    }

    @Test
    public void testEnvVariableValiditySL() throws Exception { // Using SystemLambda to test to rerieve api token from env variable
        // Use SystemLambda to mock the environment variable
        SystemLambda.withEnvironmentVariable("API_CURRENCY_TOKEN", "test-api-token-from-env-variable")
                .execute(() -> {
                    System.clearProperty("api.currency.token"); // Ensure system property is cleared before retrieving env variable
                    assertEquals("test-api-token-from-env-variable", ApiTokenManager.getApiKey());
                });
    }

    @Test
    public void testNeither(){

        System.clearProperty("api.currency.token"); // clear the system variable if set 
        System.clearProperty("API_CURRENCY_TOKEN"); // Clear the environment variable if set

       ApiTokenManager.getApiKey(); // call to show that it throws an exception

    }

}
