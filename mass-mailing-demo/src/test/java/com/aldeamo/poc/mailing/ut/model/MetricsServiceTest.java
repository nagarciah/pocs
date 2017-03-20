package com.aldeamo.poc.mailing.ut.model;

import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.Matchers.hasEntry;
import static org.hamcrest.Matchers.iterableWithSize;
import static org.junit.Assert.assertThat;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.TimeZone;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.ResourceAccessException;

import com.aldeamo.poc.mailing.model.MetricsService;
import com.aldeamo.poc.mailing.model.TimeSeriesMetricsRequest;
import com.aldeamo.poc.mailing.model.sparkpost.TimeSeriesMetricsResponse;
import com.aldeamo.poc.mailing.testutil.UnitTestCategory;

/**
 * Se utilizan las anotaciones @SpringBootTest(classes=TestSuiteClass.class) y @Configuration
 * para evitar que se cargue todo el contexto de Spring (JPA, y otros Beans) pero teniendo
 * acceso a la infraestructura de Spring, como la carga de propiedades con @Value 
 * 
 * @author nelson
 */

@RunWith(SpringRunner.class)
@SpringBootTest(classes=MetricsServiceTest.class)
@Configuration
@Category(UnitTestCategory.class)
public class MetricsServiceTest {
	public static final Log log = LogFactory.getLog(MetricsServiceTest.class);

	@Value("${sparkpost.api.metrics}") 
	String sparkpostApiMetrics;
	
	@Value("${sparkpost.api.masterKey}") 
	String sparkpostApiMasterKey; 
	
	@Value("${sparkpost.api.metrics.endpoint}") 
	String apiEndpoint;
	
	@Value("${sparkpost.api.metrics.timeSeriesUrlPattern}") 
	String metricsUrlPattern;

	
	MetricsService metricsService;
	

	private TimeSeriesMetricsRequest createTimeSeriesRequest() {
		String customerToken = "1";
		Date from = new GregorianCalendar(2016, 8, 1).getTime();
		Date to = new Date();
		TimeZone customerTimezone = TimeZone.getDefault();
		
		metricsService = new MetricsService(sparkpostApiMetrics, sparkpostApiMasterKey, apiEndpoint, metricsUrlPattern);

		return new TimeSeriesMetricsRequest(customerToken, from, to, customerTimezone);
	}
	
	
	@Test
	public void getTimeSeriesForCustomer() {
		TimeSeriesMetricsRequest request = createTimeSeriesRequest();		
		TimeSeriesMetricsResponse response = metricsService.getTimeSeries(request);
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssZ");
		assertThat(response.getResults(), iterableWithSize(greaterThan(0)));
		assertThat(response.getResults().get(0), hasEntry("ts", sdf.format(request.getFrom()).substring(0, 22) + ":00" ));
		System.out.println(response.getResults());
	}


	@Test(expected=ResourceAccessException.class)
	public void noConnectionToMetricsProvider() {
		TimeSeriesMetricsRequest request = createTimeSeriesRequest();
		
		metricsService.setApiEndpoint("http://localhost:10000/este-sitio-no-existe");
		metricsService.getTimeSeries(request);
	}
}
