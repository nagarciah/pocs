package com.aldeamo.poc.mailing.api;

import java.util.Date;
import java.util.TimeZone;

import javax.validation.Valid;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.aldeamo.poc.mailing.model.MetricsService;
import com.aldeamo.poc.mailing.model.TimeSeriesMetricsRequest;
import com.aldeamo.poc.mailing.model.sparkpost.TimeSeriesMetricsResponse;

@RestController
@RequestMapping(ApiConstants.API_MAPPING + "/metrics")
public class MetricsAPI {
	public static final Log log = LogFactory.getLog(MetricsAPI.class);
	
	private MetricsService metricsService;

	@Autowired
	public MetricsAPI(MetricsService metricsService) {
		super();
		this.metricsService = metricsService;
	}
	

	@RequestMapping(value = "/time-series/{customerId}", method = RequestMethod.GET)
	public TimeSeriesMetricsResponse getMetricsForCustomer(@Valid @PathVariable(required = true) String customerId, Date from, Date to, TimeZone customerTimezone){
		try{
			return metricsService.getTimeSeries(new TimeSeriesMetricsRequest(customerId, from, to, customerTimezone));
		} catch (Exception e) {
			// TODO Agregar más información para poder rastrear el error y
			// agilizar el diagnóstico cuando sea reportado por el cliente
			log.error(e);
			// return new EmailSendingResponse(null,
			// EmailSendingStatus.PROCESSING_ERROR);
			throw e;
		}
	}
}
