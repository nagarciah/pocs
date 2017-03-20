package com.aldeamo.poc.mailing.model;

import java.util.Date;
import java.util.TimeZone;

public class TimeSeriesMetricsRequest {
	private String customerId;
	private Date from;
	private Date to;
	private TimeZone customerTimezone;

	public TimeSeriesMetricsRequest(String customerId, Date from, Date to, TimeZone customerTimezone) {
		this.customerId = customerId;
		this.from = from;
		this.to = to;
		this.customerTimezone = customerTimezone;
	}

	public String getCustomerId() {
		return customerId;
	}

	public void setCustomerId(String customerToken) {
		this.customerId = customerToken;
	}

	public Date getFrom() {
		return from;
	}

	public void setFrom(Date from) {
		this.from = from;
	}

	public Date getTo() {
		return to;
	}

	public void setTo(Date to) {
		this.to = to;
	}

	public TimeZone getCustomerTimezone() {
		return customerTimezone;
	}

	public void setCustomerTimezone(TimeZone customerTimezone) {
		this.customerTimezone = customerTimezone;
	}
}