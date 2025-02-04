package com.example.IDF.technology.task.mapperImpl;

import com.example.IDF.technology.task.entity.ExchangeRate;
import com.example.IDF.technology.task.mapper.ForexRateMapper;
import com.example.IDF.technology.task.model.ForexRate;
import org.springframework.stereotype.Component;

/**
 * Implementation of the {@link ForexRateMapper} interface for mapping between {@link ForexRate} and {@link ExchangeRate}.
 * <p>
 * This class implements the methods defined in the {@link ForexRateMapper} interface using custom logic
 * to convert a {@link ForexRate} (an external model representing the forex data) to an {@link ExchangeRate}
 * (an internal entity for use in the application, such as in the database).
 * </p>
 */
@Component
public class ForexRateMapperImpl implements ForexRateMapper {

    /**
     * Converts a {@link ForexRate} object to an {@link ExchangeRate} entity.
     * <p>
     * This method maps the relevant fields from the {@link ForexRate} model to the corresponding fields in the
     * {@link ExchangeRate} entity, ensuring the integrity and correctness of the data transformation.
     * If a field in the {@link ForexRate} is null, it will not be mapped to the {@link ExchangeRate}.
     * </p>
     *
     * @param forexRate the {@link ForexRate} object to be converted
     * @return the mapped {@link ExchangeRate} entity or {@code null} if the input {@link ForexRate} is {@code null}
     */
    @Override
    public ExchangeRate forexRateToExchangeRate(ForexRate forexRate) {

        if (forexRate == null) {
            return null;
        }

        ExchangeRate exchangeRate = new ExchangeRate();


        exchangeRate.setDate(forexRate.getDate());
        exchangeRate.setSymbol(forexRate.getSymbol());


        if (forexRate.getClose() != null) {
            exchangeRate.setClosingRate(forexRate.getClose());
        }


        if (forexRate.getPrevious_close() != null) {
            exchangeRate.setPreviousClosingRate(forexRate.getPrevious_close());
        }

        return exchangeRate;
    }
}
