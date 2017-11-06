package com.modusoftware.oauth2.auth.clients;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Client {

    @Id
    @GeneratedValue
    private Long id;

    private String clientId;

    private String secret;

    private String scopes;

    private String authorizedGrantTypes;

    private String authorities;

    private String autoApproveScopes;
    
    private String redirectUri;

    public Client(){}
    
    public Client(String clientId, String clientSecret) {
        this.clientId = clientId;
        this.secret = clientSecret;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getClientId() {
        return clientId;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }

    public String getSecret() {
        return secret;
    }

    public void setSecret(String secret) {
        this.secret = secret;
    }

    public String getScopes() {
        return scopes;
    }

    public void setScopes(String scopes) {
        this.scopes = scopes;
    }

    public String getAuthorizedGrantTypes() {
        return authorizedGrantTypes;
    }

    public void setAuthorizedGrantTypes(String authorizedGrantTypes) {
        this.authorizedGrantTypes = authorizedGrantTypes;
    }

    public String getAuthorities() {
        return authorities;
    }

    public void setAuthorities(String authorities) {
        this.authorities = authorities;
    }

    public String getAutoApproveScopes() {
        return autoApproveScopes;
    }

    public void setAutoApproveScopes(String autoApproveScopes) {
        this.autoApproveScopes = autoApproveScopes;
    }

    public String getRedirectUri() {
        return redirectUri;
    }

    public void setRedirectUri(String redirectUri) {
        this.redirectUri = redirectUri;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("Client [id=");
        builder.append(id);
        builder.append(", clientId=");
        builder.append(clientId);
        builder.append(", secret=");
        builder.append(secret);
        builder.append(", scopes=");
        builder.append(scopes);
        builder.append(", authorizedGrantTypes=");
        builder.append(authorizedGrantTypes);
        builder.append(", authorities=");
        builder.append(authorities);
        builder.append(", autoApproveScopes=");
        builder.append(autoApproveScopes);
        builder.append(", redirectUri=");
        builder.append(redirectUri);
        builder.append("]");
        return builder.toString();
    }
}
