package com.example.demo;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class CurrencyService {
    private static Map<String, Double> exchangeRates;

    public CurrencyService() {
        this.exchangeRates = new HashMap<>();
        // You can add default exchange rates or load them from a database/file
    }

    public static double convertCurrency(double amount, String fromCurrency, String toCurrency) throws IOException {
        double fromRate = getExchangeRate(fromCurrency);
        double toRate = getExchangeRate(toCurrency);

        if (fromRate == 0.0 || toRate == 0.0) {
            throw new IllegalArgumentException("Invalid currency code");
        }

        return (amount / fromRate) * toRate;
    }

    public static double getExchangeRate(String currencyCode) throws IOException {
        if (exchangeRates.containsKey(currencyCode)) {
            return exchangeRates.get(currencyCode);
        }

        // Fetch the exchange rate from the external API
        double exchangeRate = fetchExchangeRateFromAPI(currencyCode);
        exchangeRates.put(currencyCode, exchangeRate);

        return exchangeRate;
    }

    private static double fetchExchangeRateFromAPI(String currencyCode) throws IOException {
        String apiKey = "YOUR_API_KEY";
        String apiUrl = "https://www.exchangerate-api.com/v6/latest/" + currencyCode + "?api_key=" + apiKey;

        URL url = new URL(apiUrl);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");

        int responseCode = connection.getResponseCode();
        if (responseCode == HttpURLConnection.HTTP_OK) {
            StringBuilder response = new StringBuilder();
            try (Scanner scanner = new Scanner(connection.getInputStream())) {
                while (scanner.hasNextLine()) {
                    response.append(scanner.nextLine());
                }
            }

            // Parse the JSON response and extract the exchange rate
            // Modify this code according to the response structure of your API
            double exchangeRate = parseExchangeRateFromResponse(response.toString());
            return exchangeRate;
        } else {
            throw new IOException("Failed to fetch exchange rate from the API");
        }
    }

    private static double parseExchangeRateFromResponse(String response) {
        // Parse the JSON response and extract the exchange rate value
        // Modify this code according to the response structure of your API
        // Example:
        // JSON response: {"base": "USD", "rates": {"EUR": 0.85, "GBP": 0.72}}
        // Parse "rates" object to get the exchange rate

        return 0.0; // Placeholder, replace with actual implementation
    }
}
