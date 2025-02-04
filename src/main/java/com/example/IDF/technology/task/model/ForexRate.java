package com.example.IDF.technology.task.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Represents the forex rate data for a specific stock symbol, including market data such as open, high, low,
 * close prices, trading volume, and more. This class is used to model the data returned from an external API,
 * providing detailed information about market performance.
 * <p>
 * It contains data about the stock symbol, exchange information, the forex market status, as well as historical
 * data, such as 52-week highs and lows, and price changes.
 * </p>
 */
public class ForexRate {

    @JsonProperty("symbol")
    private String symbol;

    @JsonProperty("name")
    private String name;

    @JsonProperty("exchange")
    private String exchange;

    @JsonProperty("mic_code")
    private String mic_code;

    @JsonProperty("currency")
    private String currency;

    @JsonFormat(pattern = "yyyy-MM-dd")
    @JsonProperty("datetime")
    private LocalDate date;

    @JsonProperty("timestamp")
    private long timestamp;

    @JsonProperty("open")
    private double open;

    @JsonProperty("high")
    private double high;

    @JsonProperty("low")
    private double low;

    @JsonProperty("close")
    private BigDecimal close;

    @JsonProperty("volume")
    private long volume;

    @JsonProperty("previous_close")
    private BigDecimal previous_close;

    @JsonProperty("change")
    private double change;

    @JsonProperty("percent_change")
    private double percent_change;

    @JsonProperty("average_volume")
    private long average_volume;

    @JsonProperty("is_market_open")
    private boolean is_market_open;

    @JsonProperty("fifty_two_week")
    private FiftyTwoWeekData fifty_two_week;

    public ForexRate(String symbol, BigDecimal close) {
        this.symbol = symbol;
        this.close = close;
    }

    /**
     * Gets the symbol of the forex rate (e.g., "AAPL" for Apple).
     *
     * @return the stock symbol
     */
    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    /**
     * Gets the name of the company or stock represented by this forex rate.
     *
     * @return the name of the company
     */
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    /**
     * Gets the exchange where the forex rate is listed.
     *
     * @return the exchange name
     */
    public String getExchange() {
        return exchange;
    }

    public void setExchange(String exchange) {
        this.exchange = exchange;
    }

    /**
     * Gets the MIC (Market Identifier Code) for the exchange.
     *
     * @return the MIC code
     */
    public String getMic_code() {
        return mic_code;
    }

    public void setMic_code(String mic_code) {
        this.mic_code = mic_code;
    }

    /**
     * Gets the currency code used in the forex rate (e.g., "USD").
     *
     * @return the currency code
     */
    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    /**
     * Gets the date of the forex rate data in the format "yyyy-MM-dd".
     *
     * @return the date of the data
     */
    public LocalDate getDate() {
        return date;
    }

    public void setDate(String dateStr) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        this.date = LocalDate.parse(dateStr, formatter);
    }

    /**
     * Gets the timestamp when the forex rate was recorded.
     *
     * @return the timestamp
     */
    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }

    /**
     * Gets the opening price for the forex rate.
     *
     * @return the opening price
     */
    public double getOpen() {
        return open;
    }

    public void setOpen(double open) {
        this.open = open;
    }

    /**
     * Gets the highest price for the forex rate during the trading period.
     *
     * @return the high price
     */
    public double getHigh() {
        return high;
    }

    public void setHigh(double high) {
        this.high = high;
    }

    /**
     * Gets the lowest price for the forex rate during the trading period.
     *
     * @return the low price
     */
    public double getLow() {
        return low;
    }

    public void setLow(double low) {
        this.low = low;
    }

    /**
     * Gets the closing price of the forex rate.
     *
     * @return the closing price
     */
    public BigDecimal getClose() {
        return close;
    }

    public void setClose(BigDecimal close) {
        this.close = close;
    }

    /**
     * Gets the total trading volume for the forex rate.
     *
     * @return the trading volume
     */
    public long getVolume() {
        return volume;
    }

    public void setVolume(long volume) {
        this.volume = volume;
    }

    /**
     * Gets the previous closing price of the forex rate.
     *
     * @return the previous closing price
     */
    public BigDecimal getPrevious_close() {
        return previous_close;
    }

    public void setPrevious_close(BigDecimal previous_close) {
        this.previous_close = previous_close;
    }

    /**
     * Gets the price change from the previous trading session.
     *
     * @return the price change
     */
    public double getChange() {
        return change;
    }

    public void setChange(double change) {
        this.change = change;
    }

    /**
     * Gets the percentage change in price from the previous trading session.
     *
     * @return the percentage change
     */
    public double getPercent_change() {
        return percent_change;
    }

    public void setPercent_change(double percent_change) {
        this.percent_change = percent_change;
    }

    /**
     * Gets the average trading volume over a specific time period.
     *
     * @return the average trading volume
     */
    public long getAverage_volume() {
        return average_volume;
    }

    public void setAverage_volume(long average_volume) {
        this.average_volume = average_volume;
    }

    /**
     * Gets whether the market for this forex rate is currently open.
     *
     * @return true if the market is open, false otherwise
     */
    public boolean isIs_market_open() {
        return is_market_open;
    }

    public void setIs_market_open(boolean is_market_open) {
        this.is_market_open = is_market_open;
    }

    /**
     * Gets the 52-week data for the forex rate, including high/low prices and percentage changes.
     *
     * @return the 52-week data
     */
    public FiftyTwoWeekData getFifty_two_week() {
        return fifty_two_week;
    }

    public void setFifty_two_week(FiftyTwoWeekData fifty_two_week) {
        this.fifty_two_week = fifty_two_week;
    }

    /**
     * Represents 52-week market data for a stock symbol, including price changes and percentage changes.
     */
    public static class FiftyTwoWeekData {

        @JsonProperty("low")
        private double low;

        @JsonProperty("high")
        private double high;

        @JsonProperty("low_change")
        private double low_change;

        @JsonProperty("high_change")
        private double high_change;

        @JsonProperty("low_change_percent")
        private double low_change_percent;

        @JsonProperty("high_change_percent")
        private double high_change_percent;

        @JsonProperty("range")
        private String range;

        /**
         * Gets the 52-week low price.
         *
         * @return the low price in the last 52 weeks
         */
        public double getLow() {
            return low;
        }

        public void setLow(double low) {
            this.low = low;
        }

        /**
         * Gets the 52-week-high price.
         *
         * @return the high price in the last 52 weeks
         */
        public double getHigh() {
            return high;
        }

        public void setHigh(double high) {
            this.high = high;
        }

        /**
         * Gets the change in the 52-week low price.
         *
         * @return the change in low price
         */
        public double getLow_change() {
            return low_change;
        }

        public void setLow_change(double low_change) {
            this.low_change = low_change;
        }

        /**
         * Gets the change in the 52-week-high price.
         *
         * @return the change in high price
         */
        public double getHigh_change() {
            return high_change;
        }

        public void setHigh_change(double high_change) {
            this.high_change = high_change;
        }

        /**
         * Gets the percentage change in the 52-week low price.
         *
         * @return the percentage change in low price
         */
        public double getLow_change_percent() {
            return low_change_percent;
        }

        public void setLow_change_percent(double low_change_percent) {
            this.low_change_percent = low_change_percent;
        }

        /**
         * Gets the percentage change in the 52-week-high price.
         *
         * @return the percentage change in high price
         */
        public double getHigh_change_percent() {
            return high_change_percent;
        }

        public void setHigh_change_percent(double high_change_percent) {
            this.high_change_percent = high_change_percent;
        }

        /**
         * Gets the range of the 52-week prices.
         *
         * @return the range (low - high) of the last 52 weeks
         */
        public String getRange() {
            return range;
        }

        public void setRange(String range) {
            this.range = range;
        }
    }
}
