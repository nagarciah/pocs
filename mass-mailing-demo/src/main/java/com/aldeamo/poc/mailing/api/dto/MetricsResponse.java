package com.aldeamo.poc.mailing.api.dto;

import java.util.List;

public class MetricsResponse {
	private List<TimeSerie> dataset;

	public class TimeSerie {
		String metricId;
		List<double[][]> data;

		public String getMetricId() {
			return metricId;
		}

		public void setMetricId(String metricId) {
			this.metricId = metricId;
		}

		public List<double[][]> getData() {
			return data;
		}

		public void setData(List<double[][]> data) {
			this.data = data;
		}
	}

	public List<TimeSerie> getDataset() {
		return dataset;
	}

	public void setDataset(List<TimeSerie> dataset) {
		this.dataset = dataset;
	}

}
