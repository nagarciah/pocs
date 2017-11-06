package integration;

import java.util.Arrays;

import org.junit.Test;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.security.oauth2.client.DefaultOAuth2ClientContext;
import org.springframework.security.oauth2.client.OAuth2RestTemplate;
import org.springframework.security.oauth2.client.token.grant.password.ResourceOwnerPasswordResourceDetails;

import com.fasterxml.jackson.databind.JsonNode;

public class OAuth2ClientTests {

    private String port = "8080";

    @Test
    public void clientAccess() {
        ResourceOwnerPasswordResourceDetails resourceDetails = new ResourceOwnerPasswordResourceDetails();
        resourceDetails.setUsername("jlong");
        resourceDetails.setPassword("spring");
        resourceDetails.setAccessTokenUri(String.format("http://localhost:%s/uaa/oauth/token", port));
        resourceDetails.setClientId("html5");
        resourceDetails.setClientSecret("password");
        resourceDetails.setGrantType("password");
        //resourceDetails.setScope(Arrays.asList("read", "write"));
        resourceDetails.setScope(Arrays.asList("openid"));

        DefaultOAuth2ClientContext clientContext = new DefaultOAuth2ClientContext();

        OAuth2RestTemplate restTemplate = new OAuth2RestTemplate(resourceDetails, clientContext);
        restTemplate.setMessageConverters(Arrays.asList(new MappingJackson2HttpMessageConverter()));

        final JsonNode greeting = restTemplate.getForObject(String.format("http://localhost:%s/greet/nelson", "8082" ), JsonNode.class);

        System.out.println(greeting);
    }
}
