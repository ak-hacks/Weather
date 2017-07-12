package com.develogical;

import com.develogical.domain.ForecastReport;

public interface ForecasterService {

    public ForecastReport getForecast(String region, String day);
}
