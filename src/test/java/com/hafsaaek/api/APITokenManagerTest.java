package com.hafsaaek.api;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class APITokenManagerTest {

    @Test
    public void testSytemPropertyValidity(){

        System.setProperty("api.currency.token", "test-api-token"); // set a mock system property

        assertEquals("test-api-token",ApiTokenManager.getApiKey());

        System.clearProperty("api.currency.token"); // clear the system property
    }


    @Test
    public void testEnvVariableValidity(){
        
    }


    @Test
    public void testNeither(){
        
    }

    
}
