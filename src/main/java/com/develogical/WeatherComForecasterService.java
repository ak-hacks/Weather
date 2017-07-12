package com.develogical;

import com.develogical.domain.ForecastAdapter;
import com.develogical.domain.ForecastReport;
import com.weather.Day;
import com.weather.Forecast;
import com.weather.Forecaster;
import com.weather.Region;

/**
 * Created by ape08 on 7/12/17.
 */
public class WeatherComForecasterService implements ForecasterService {

    private final Forecaster forecaster;

    public WeatherComForecasterService(Forecaster forecaster) {
        this.forecaster = forecaster;
    }

    @Override
    public ForecastReport getForecast(String region, String day) {
        Forecast remoteForecast = forecaster.forecastFor(Region.valueOf(region.toUpperCase()), Day.valueOf(day.toUpperCase()));
        ForecastAdapter forecastAdapter = new ForecastAdapter();
        return forecastAdapter.convert(remoteForecast);
    }
}
