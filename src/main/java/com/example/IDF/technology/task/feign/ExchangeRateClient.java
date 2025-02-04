package com.example.IDF.technology.task.feign;

import com.example.IDF.technology.task.model.ForexRate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Feign client interface for interacting with the Twelve Data API to retrieve exchange rates.
 * <p>
 * This interface defines the method for fetching exchange rates for a given symbol (e.g., stock or currency pair) using the Twelve Data API.
 * The exchange rate data is returned as a {@link ForexRate} object.
 * The URL for the API is configurable and is read from the application's properties file, allowing flexibility in configuring different environments (e.g., development, production).
 * </p>
 */
@FeignClient(name = "exchangeRateClient", url = "${api.twelve.data.url}")
public interface ExchangeRateClient {

    /**
     * Fetches the exchange rate for the given symbol (e.g., "USD/EUR") from the Twelve Data API.
     * <p>
     * This method calls the Twelve Data API's quote endpoint and retrieves the exchange rate data for the specified symbol.
     * The exchange rate is returned as a {@link ForexRate} object, which contains details about the currency pair's price and related information.
     * </p>
     *
     * @param symbol the symbol representing the exchange rate pair (e.g., "USD/EUR")
     * @param apiKey the API key for accessing the Twelve Data service
     * @return a {@link ForexRate} object containing the exchange rate information for the given symbol
     */
    @GetMapping("/quote")
    ForexRate getExchangeRate(@RequestParam("symbol") String symbol, @RequestParam("apikey") String apiKey);
}
