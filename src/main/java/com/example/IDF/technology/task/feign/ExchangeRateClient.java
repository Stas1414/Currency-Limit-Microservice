package com.example.IDF.technology.task.feign;

import com.example.IDF.technology.task.entity.ExchangeRate;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;
import java.util.Objects;

@Component
@FeignClient(name = "exchangeRateClient", url = "https://api.twelvedata.com")
public interface ExchangeRateClient {
    @GetMapping("/quote")
    Map<String, Object> getExchangeRate(@RequestParam("symbol") String symbol, @RequestParam("apikey") String apiKey);
}
