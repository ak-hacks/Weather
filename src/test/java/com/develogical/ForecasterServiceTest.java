package com.develogical;

import com.develogical.domain.ForecastReport;
import com.weather.Day;
import com.weather.Forecast;
import com.weather.Forecaster;
import com.weather.Region;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

public class ForecasterServiceTest {

    private ForecasterService forecasterService;

    @Before
    public void setUp() throws Exception {
        forecasterService = Mockito.mock(ForecasterService.class);
        ForecastReport forecastReport = new ForecastReport("sunny", 25);
        Mockito.when(forecasterService.getForecast(Mockito.anyString(), Mockito.anyString())).thenReturn(forecastReport);
    }

    @Test
    public void forecasterServiceShouldReturnForecastForGivenRegionAndDay() {
        ForecastReport forecastReport = forecasterService.getForecast("London", "Monday");
        Assert.assertEquals("sunny", forecastReport.getSummary());
        Assert.assertEquals(25, forecastReport.getTemperature());
    }

    @Test
    public void forecasterServiceShouldReturnCachedResultsIfResultIsInCache() {

        // given result is in cache
        forecasterService.getForecast("London", "Monday");

        // when we invoke the service again
        ForecastReport forecastReport = new ForecastReport("sunny", 25);
        Mockito.when(forecasterService.getForecast(Mockito.anyString(), Mockito.anyString())).thenReturn(forecastReport);

        // then the result should be from the originally cached value
        Assert.assertEquals("sunny", forecastReport.getSummary());
        Assert.assertEquals(25, forecastReport.getTemperature());
    }
}
