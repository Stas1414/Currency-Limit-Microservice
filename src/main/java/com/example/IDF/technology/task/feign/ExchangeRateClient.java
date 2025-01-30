package com.example.IDF.technology.task.feign;

import com.example.IDF.technology.task.entity.ExchangeRate;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Component
@FeignClient(name = "exchangeRateClient", url = "https://api.twelvedata.com")
public interface ExchangeRateClient {
    @GetMapping("/quote")
    ExchangeRate getExchangeRate(@RequestParam("symbol") String symbol, @RequestParam("interval") String interval, @RequestParam("apikey") String apiKey);
}
