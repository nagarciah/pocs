package com.aldeamo.poc.mailing.model.sparkpost;

import java.util.List;
import java.util.Map;

public class TimeSeriesMetricsResponse {
	private List<Map<String, String>>results;

	public List<Map<String, String>> getResults() {
		return results;
	}

	public void setResults(List<Map<String, String>> results) {
		this.results = results;
	}

	@Override
	public String toString() {
		return "TimeSeriesMetricsResponse [results=" + results + "]";
	}
}
