package com.hafsaaek;

public class ParseJSONResponse {
    
    JSONObject object = new JSONObject(response.toString());

    // the sample JSOB objct is the below and we only need the value key's value  
        // "GBP": {
        //     "code": "GBP",
        //     "value": 0.7699900777
        //   }

    Double exchangeRate = object.getJSONObject('value');
    System.out.println(exchangeRate);
    
}

