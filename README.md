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
'java -Dapi.currency.token=$API_CURRENCY_TOKEN -cp . com.hafsaaek.CurrencyConverter'

## How to store the api token as an environment variable:
1. Open your terminal and edit your shell configuration file:
   - For **Bash**: `~/.bash_profile` or `~/.bashrc`
   - For **Zsh**: `~/.zshrc`
2. Add the following line to set the API token as an environment variable:
   ```bash
   export API_CURRENCY_TOKEN="YOUR_API_KEY_HERE"


## Testing
#### Ideas:
1. Chekcing last udpated in metadata


## MAVEN 
1. Build project using VS Code - use CMP+P to build a
2. install maven using brew - `brew install maven`
3. Check if it's installed using `mvn -version` - if it's not, you might need to define the JAVA_HOME env variable in your shell profile:
    1. First, you need to find where Java is installed on your system. You can do this by running: /usr/libexec/java_home
    2. Set your java_home env variable in your shell profile e.g., in bash, run `nano ~/.bash_profile`
    3. Copy the below to your JAVA_HOME VARIABLE
        # Custom alias
        alias hw='echo "Hello from bash_profile"'
w
        # Set JAVA_HOME explicitly (replace with your actual path)
        export JAVA_HOME=/usr/local/Cellar/openjdk/22.0.2/libexec/openjdk.jdk/Contents/Home

        # Add JAVA_HOME to PATH
        export PATH=$JAVA_HOME/bin:$PATH
        
    4. To save changes: `source ~/.bash_profile`
4. Create maven wrapper in your repo - ` mvn -N io.takari:maven:wrapper` this will 
5. Compile your project: `mvn clean compile`
5. Run your proejct with maven:  `mvn exec:java -Dexec.mainClass="com.yourpackage.YourMainClass"` 
e.g., if your main class was main.java, then use `mvn exec:java -Dexec.mainClass="com.hafsaaek.main"``
