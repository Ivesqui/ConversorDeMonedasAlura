package org.example;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;
import org.json.JSONObject;

public class CurrencyConverter {
    private static final String API_URL = "https://v6.exchangerate-api.com/v6/";
    private String apiKey;

    public CurrencyConverter(String apiKey) {
        this.apiKey = apiKey;
    }

    public double convertCurrency(double amount, String fromCurrency, String toCurrency) throws IOException {
        String urlStr = API_URL + apiKey + "/latest/" + fromCurrency.toUpperCase();
        URL url = new URL(urlStr);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");

        int responseCode = connection.getResponseCode();
        if (responseCode == HttpURLConnection.HTTP_OK) {
            Scanner scanner = new Scanner(url.openStream());
            StringBuilder response = new StringBuilder();
            while (scanner.hasNext()) {
                response.append(scanner.nextLine());
            }
            scanner.close();

            JSONObject jsonObject = new JSONObject(response.toString());
            double exchangeRate = jsonObject.getJSONObject("conversion_rates").getDouble(toCurrency.toUpperCase());
            return amount * exchangeRate;
        } else {
            throw new IOException("Failed to fetch data from API: " + responseCode);
        }

}
}
