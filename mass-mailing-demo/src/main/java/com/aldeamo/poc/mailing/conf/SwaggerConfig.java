package com.aldeamo.poc.mailing.conf;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.aldeamo.poc.mailing.api.ApiConstants;

import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;


@Configuration
@EnableSwagger2
public class SwaggerConfig {                                    
    @Bean
    public Docket api() { 
        return new Docket(DocumentationType.SWAGGER_2)  
          .select()                                  
          //.apis(RequestHandlerSelectors.any())
          .apis(RequestHandlerSelectors.basePackage("com.aldeamo.poc.mailing.api"))
          .paths(PathSelectors.any())
          .build()
          .apiInfo(getApiInfo());                                           
    }
    
    private ApiInfo getApiInfo() {
    	return new ApiInfo(
        		"AldeaMail API", 
        		"API REST de Aldeamo para el envío de correos electrónicos masivos comerciales o transaccionales.",
        		ApiConstants.API_VERSION, 
        		"termsOfServiceUrl", 
        		new Contact("Aldeamo", "http://www.aldemo.com/", "noc@aldeamo.com"),
        		"License", 
        		"licenseUrl"); 
    }
    
    @Controller
    public static class ApiDocsController {

        /**
         * Redirige a la documentación
         * @return
         */
    	@RequestMapping("/api/docs")
        public String home() {
            return "redirect:/swagger-ui.html";
        }
    }
}