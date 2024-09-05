package com.hafsaaek.api;

import static org.mockito.Mockito.*;

import java.net.HttpURLConnection;
import java.net.URI;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import static org.junit.Assert.assertEquals;

public class APIServiceTest {
    private APIService apiService;
    private ApiTokenManager apiTokenManager;
    private String fromCode;
    private String toCode;
    private String expectedURL;

    @SuppressWarnings("static-access")
    @Before
    public void setUpMock(){

        apiTokenManager = mock(ApiTokenManager.class); // create a mock object of apitokenmanager class

        when(apiTokenManager.getApiKey()).thenReturn("dummy-api-key");

        apiService = new APIService(apiTokenManager); // make an instance of the APIServie class using hte mock api token manager 

        fromCode = "GBP";
        toCode = "USD";
        expectedURL= "https://api.currencyapi.com/v3/latest?apikey=dummy-api-key" + "&base_currency=" + fromCode + "&currencies=" + toCode;
    }

    @Test 
    // @Description("GET request returns 404 not found response")
    public void getInvalidResponse () throws Exception { 
        URI uri = new URI(expectedURL); // create a URI object from the expectered URI

        HttpURLConnection mockHttpURLConnection = mock(HttpURLConnection.class); // create a mock of the http url connection to control response

        when(uri.toURL().openConnection()).thenReturn((mockHttpURLConnection));

        when(mockHttpURLConnection.getResponseCode()).thenReturn(HttpURLConnection.HTTP_NOT_FOUND);

        int responseCode = mockHttpURLConnection.getResponseCode();

        assertEquals(HttpURLConnection.HTTP_NOT_FOUND, responseCode);
    }

    @Test
    public void getInternalServerError() throws Exception{

        URI uri = new URI(expectedURL);

        HttpURLConnection mockhttpconnection =  mock(HttpURLConnection.class);

        when(uri.toURL().openConnection()).thenReturn(mockhttpconnection);

        when(mockhttpconnection.getResponseCode()).thenReturn(HttpURLConnection.HTTP_INTERNAL_ERROR);

        int responseCode = mockhttpconnection.getResponseCode();

        assertEquals(HttpURLConnection.HTTP_INTERNAL_ERROR, responseCode);

    }

    
}
