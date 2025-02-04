package com.example.IDF.technology.task.mapper;

import com.example.IDF.technology.task.entity.ExchangeRate;
import com.example.IDF.technology.task.model.ForexRate;
import org.mapstruct.Mapper;

/**
 * Mapper interface for converting between {@link ForexRate} and {@link ExchangeRate} objects.
 * <p>
 * This interface uses MapStruct to automatically generate the implementation for converting data between
 * the external ForexRate model (received from the API) and the internal ExchangeRate entity (used in the application).
 * The generated implementation will handle mapping the relevant fields from the {@link ForexRate} to {@link ExchangeRate}
 * and vice versa, simplifying the conversion process and ensuring type safety.
 * </p>
 */
@Mapper
public interface ForexRateMapper {

    /**
     * Converts a {@link ForexRate} object (from the external API) to an {@link ExchangeRate} entity (for internal use).
     * <p>
     * This method maps fields from the ForexRate model (such as currency, open, close, and high values) to the corresponding
     * fields in the ExchangeRate entity. The ExchangeRate object is typically saved in the database for later use.
     * </p>
     *
     * @param forexRate the ForexRate object to be converted
     * @return the mapped {@link ExchangeRate} entity
     */
    ExchangeRate forexRateToExchangeRate(ForexRate forexRate);
}
