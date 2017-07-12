package com.develogical.domain;

import com.weather.Forecast;

public class ForecastAdapter {

    public ForecastReport convert(Forecast remoteForecast) {
        return new ForecastReport(remoteForecast.summary(), remoteForecast.temperature());
    }
}
