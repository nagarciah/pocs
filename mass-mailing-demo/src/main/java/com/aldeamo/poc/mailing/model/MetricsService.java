package com.aldeamo.poc.mailing.model;

import java.text.MessageFormat;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.TimeZone;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.aldeamo.poc.mailing.model.sparkpost.TimeSeriesMetricsResponse;

@Service
public class MetricsService {
	public static final Log log = LogFactory.getLog(MetricsService.class);
	
	@Autowired
	private ApiUserService apiUserService;

	// TODO Estas variables y la lógica asociada a ellas, genera acoplamiento con Sparkpost. 
	// Debe reemplazarse por un factory de proveedores de email y sus métricas 
	private String sparkpostApiMetrics;
	private String sparkpostApiMasterKey;
	private String apiEndpoint;
	private String metricsUrlPattern;
	
	private SimpleDateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm");
	

	public MetricsService(
			@Value("${sparkpost.api.metrics}") String sparkpostApiMetrics, 
			@Value("${sparkpost.api.masterKey}") String sparkpostApiMasterKey, 
			@Value("${sparkpost.api.metrics.endpoint}") String apiEndpoint,
			@Value("${sparkpost.api.metrics.timeSeriesUrlPattern}") String metricsUrlPattern) 
	{
		super();
		this.sparkpostApiMetrics = sparkpostApiMetrics;
		this.sparkpostApiMasterKey = sparkpostApiMasterKey;
		this.apiEndpoint = apiEndpoint;
		this.metricsUrlPattern = metricsUrlPattern;
	}
	
	
	public TimeSeriesMetricsResponse getTimeSeries(TimeSeriesMetricsRequest request){
		// Si no hay rango seleccionado, se retornan métricas diarias para los últimos 10 días
		// Si no hay zona horaria, se utiliza la del servidor
		if(request.getTo()==null){
			request.setTo(new Date());
		}
		
		if(request.getFrom() == null || request.getTo().before( request.getFrom() )){
			Calendar toCal = new GregorianCalendar();
			toCal.add(Calendar.DATE, -10);
			request.setFrom( toCal.getTime() );
		}
		
		if(request.getCustomerTimezone() == null){
			request.setCustomerTimezone( TimeZone.getDefault() );
		}
		
		// Prepara parámetros de consulta
		UserInfo userInfo = apiUserService.findOneByCustomerId(request.getCustomerId());
		String fromParam = dateFormatter.format(request.getFrom());
		String toParam = dateFormatter.format(request.getTo());
		String timezoneParam = request.getCustomerTimezone().getID();
		String getURL = MessageFormat.format(metricsUrlPattern, apiEndpoint, fromParam, toParam, sparkpostApiMetrics, timezoneParam, userInfo.getProviderSubaccountId()); 
		
		if(log.isDebugEnabled()){
			log.debug("Consultando URL de métricas por series de tiempo en SparkPost: " + getURL);
		}
			
		// Consulta el servicio
		RestTemplate restTemplate = new RestTemplate();
		HttpHeaders headers = new HttpHeaders();
		headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
		headers.set("Authorization", sparkpostApiMasterKey);
		HttpEntity<String> entity = new HttpEntity<>(headers);

		ResponseEntity<TimeSeriesMetricsResponse> response = 
				restTemplate.exchange(getURL, HttpMethod.GET, entity, TimeSeriesMetricsResponse.class);		 
		
		
		return response.getBody();
	}
	


	public String getApiEndpoint() {
		return apiEndpoint;
	}
	
	public void setApiEndpoint(String apiEndpoint) {
		this.apiEndpoint = apiEndpoint;
	}
}
