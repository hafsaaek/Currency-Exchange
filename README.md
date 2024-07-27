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

#### Logic:
    1. Initialization: ApiTokenManager class initializes static fields, including INITIALIZED_API_KEY which is set by calling loadApiKey().
    2. loadApiKey() Method: Fetches and validates the API key from system properties. Throws an exception if the key is missing or empty.
    3. API Key Assignment: INITIALIZED_API_KEY is assigned the valid API key.
    4. Retrieval: getApiKey() method returns the initialized API key.
    5. Usage: CurrencyConverter class retrieves the API key via ApiTokenManager.getApiKey() for making API requests.

## How to store the api token as a system proeprty:
1. Set the API key as a system property using the command:
`export api.currency.token=YOUR_API_KEY_HERE`
2. Replace YOUR_API_KEY_HERE with your actual API key.
3. Run the Application. The application will automatically use the API key to perform currency conversions.
