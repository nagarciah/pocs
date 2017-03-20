package nagarciah.test.spring.properties;

import java.util.Map;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/application-config.xml")
public class SpringPropertiesTest {
	
	@Autowired
	PropertiesMonitor props;

	@Test
	public void test() {
		System.out.println("OS Property: " + props.getOsProperty());
		/*
        Map<String, String> env = System.getenv();
        for (String envName : env.keySet()) {
            System.out.format("%s=%s%n",
                              envName,
                              env.get(envName));
        }*/
	}

}
