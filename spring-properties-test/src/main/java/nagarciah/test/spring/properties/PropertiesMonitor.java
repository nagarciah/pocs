package nagarciah.test.spring.properties;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

//@Component
public class PropertiesMonitor {
	//@Value("${JDK_HOME}")
	String osProperty;

	public String getOsProperty() {
		return osProperty;
	}

	public void setOsProperty(String osProperty) {
		this.osProperty = osProperty;
	}
}
