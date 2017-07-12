package com.develogical.domain;

/**
 * Created by ape08 on 7/12/17.
 */
public class ForecastReport {

    private final String summary;
    private final int temperature;

    public ForecastReport(String summary, int temperature) {
        this.summary = summary;
        this.temperature = temperature;
    }

    public String getSummary() {
        return summary;
    }

    public int getTemperature() {
        return temperature;
    }
}
