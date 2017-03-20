package nagarciah.test.jasper.service.reporting.impl;

import nagarciah.test.jasper.service.reporting.Reporter;
import nagarciah.test.jasper.service.reporting.ReporterFactory;

public class JasperReporterFactory extends ReporterFactory {

	@Override
	public Reporter createReporter() {
		return new JasperReporter();
	}

}
