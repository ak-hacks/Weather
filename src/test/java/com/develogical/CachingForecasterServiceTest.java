package com.develogical;

import com.develogical.domain.ForecastReport;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

public class CachingForecasterServiceTest {
    private ForecasterService delegate;
    private ForecasterService cachingService;

    @Before
    public void setUp() throws Exception {
        delegate = Mockito.mock(ForecasterService.class);
        cachingService = new CachingForecasterService(delegate);
        ForecastReport forecastReport = new ForecastReport("sunny", 25);
        Mockito.when(delegate.getForecast(Mockito.anyString(), Mockito.anyString())).thenReturn(forecastReport);
    }

    @Test
    public void forecasterServiceShouldReturnForecastForGivenRegionAndDay() {
        ForecastReport forecastReport = cachingService.getForecast("London", "Monday");
        Assert.assertEquals("sunny", forecastReport.getSummary());
        Assert.assertEquals(25, forecastReport.getTemperature());
    }

    @Test
    public void forecasterServiceShouldReturnCachedResultsIfResultIsInCache() {
        // given result is in cache
        cachingService.getForecast("London", "Monday");

        // when we invoke the service again
        ForecastReport forecastReport = new ForecastReport("rainy", 9);
        Mockito.when(delegate.getForecast(Mockito.anyString(), Mockito.anyString())).thenReturn(forecastReport);

        ForecastReport report = cachingService.getForecast("London", "Monday");

        // then the result should be from the originally cached value
        Assert.assertEquals("sunny", report.getSummary());
        Assert.assertEquals(25, report.getTemperature());
        verify(delegate, times(1)).getForecast("London", "Monday");
    }
}
