package com.example.demo;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;

public class Controller {
    @FXML
    private TextField amountText;
    @FXML
    private ComboBox<String> fromBox;
    @FXML
    private ComboBox<String> toBox;
    @FXML
    private Button convertBtn;
    @FXML
    private TextField resultField;
    @FXML
    private TextField rateField;

    // Define your conversion rates here (e.g., mapping from currency code to rate)
    private static final double USD_TO_EUR = 0.85;
    private static final double USD_TO_GBP = 0.75;
    // Add more conversion rates as needed

    @FXML
    private void initialize() {
        // Populate the ComboBox options with currency codes
        fromBox.getItems().addAll("USD", "EUR", "GBP"); // Add more currency codes as needed
        toBox.getItems().addAll("USD", "EUR", "GBP"); // Add more currency codes as needed
    }

    @FXML
    private void convert(ActionEvent event) {
        String fromCurrency = fromBox.getValue();
        String toCurrency = toBox.getValue();
        double amount = Double.parseDouble(amountText.getText());

        double rate = getConversionRate(fromCurrency, toCurrency);
        double result = amount * rate;

        resultField.setText(String.format("%.2f", result));
        rateField.setText(String.format("%.4f", rate));
    }

    private double getConversionRate(String fromCurrency, String toCurrency) {
        // Define your conversion rates based on the fromCurrency and toCurrency
        if (fromCurrency.equals("USD") && toCurrency.equals("EUR")) {
            return USD_TO_EUR;
        } else if (fromCurrency.equals("USD") && toCurrency.equals("GBP")) {
            return USD_TO_GBP;
        } else if (fromCurrency.equals(toCurrency)) {
            return 1.0; // Same currency, no conversion needed
        } else {
            // Handle unsupported conversion rates
            throw new IllegalArgumentException("Unsupported conversion: " + fromCurrency + " to " + toCurrency);
        }
    }
    @FXML
    private void handleConvertButtonAction() {
        String amountStr = amountText.getText();
        String fromCurrency = fromBox.getValue();
        String toCurrency = toBox.getValue();

        // Validate input values
        if (amountStr.isEmpty() || fromCurrency == null || toCurrency == null) {
            // Handle invalid input, display an error message or take appropriate action
            return;
        }

        try {
            double amount = Double.parseDouble(amountStr);

            // Perform the currency conversion using the selected currencies and amount
            double convertedAmount = performCurrencyConversion(amount, fromCurrency, toCurrency);

            // Update the result field and rate field
            resultField.setText(String.format("%.2f", convertedAmount));
            rateField.setText(getExchangeRate(fromCurrency, toCurrency));
        } catch (NumberFormatException e) {
            // Handle invalid amount format, display an error message or take appropriate action
        }
    }

    private double performCurrencyConversion(double amount, String fromCurrency, String toCurrency) {
        // Implement the currency conversion logic here
        // You can use an in-memory database or an external API to retrieve the exchange rates
        // Calculate the converted amount based on the selected currencies and the amount

        // Example implementation: Assume the exchange rate is 1.2
        double exchangeRate = 1.2;
        return amount * exchangeRate;
    }

    private String getExchangeRate(String fromCurrency, String toCurrency) {
        // Implement the code to retrieve the exchange rate between the specified currencies
        // You can use an in-memory database or an external API to get the exchange rate

        // Example implementation: Assume the exchange rate is 1.2
        return "1.2";
    }

}
