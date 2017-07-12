package com.develogical;

import com.develogical.domain.ForecastReport;
import com.develogical.domain.ForecastAdapter;
import com.weather.Day;
import com.weather.Forecaster;
import com.weather.Region;

import java.util.HashMap;
import java.util.Map;

public class CachingForecasterService implements ForecasterService {

    private Map<String, ForecastReport> cache;
    private ForecasterService forecasterService;

    public CachingForecasterService(ForecasterService forecasterService) {
        this.forecasterService = forecasterService;
        cache = new HashMap<>();
    }

    @Override
    public ForecastReport getForecast(String region, String day) {

        final ForecastReport cachedForecastReport = getFromCache(region, day);

        if (cachedForecastReport != null) {
            return cachedForecastReport;
        } else {
            ForecastReport forecastReport = forecasterService.getForecast(region, day);
            setToCache(region, day, forecastReport);
            return forecastReport;
        }
    }

    private ForecastReport getFromCache(String region, String day) {
        return cache.get(region+day);
    }

    private void setToCache(String region, String day, ForecastReport forecastReport) {
        cache.put(region+day, forecastReport);
    }

}
