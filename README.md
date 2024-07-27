# Currency Exchange

## Overview

This project facilitates currency conversion using a third-party API. To interact with the API, the application requires an API key, which is managed through the `ApiTokenManager` class.

## API Key Management

The following flowchart illustrates the process of retrieving and using the API key:

```plaintext
+-------------------------------+
| Start                         |
+-------------------------------+
                |
                v
+-------------------------------+
| Load ApiTokenManager Class    |
| - Initialize static fields    |
|   - API_PROPERTY_KEY = "api.currency.token" |
|   - INITIALIZED_API_KEY = loadApiKey()       |
+-------------------------------+
                |
                v
+-------------------------------+
| Method: loadApiKey()          |
| - Retrieve API key from System Property     |
| - Validate API key                         |
|   - Throw exception if API key is null or empty |
| - Return API key                           |
+-------------------------------+
                |
                v
+-------------------------------+
| Assign INITIALIZED_API_KEY    |
| = Returned API key from loadApiKey()        |
+-------------------------------+
                |
                v
+-------------------------------+
| Method: getApiKey()           |
| - Return INITIALIZED_API_KEY  |
+-------------------------------+
                |
                v
+-------------------------------+
| CurrencyConverter Class       |
| - Call ApiTokenManager.getApiKey()       |
| - Retrieve and use API key for requests    |
+-------------------------------+
                |
                v
+-------------------------------+
| End                           |
+-------------------------------+
