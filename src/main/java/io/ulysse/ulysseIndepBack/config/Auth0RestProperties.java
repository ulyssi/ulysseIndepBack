package io.ulysse.ulysseIndepBack.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "appli.oakrest.auth0")
public class Auth0RestProperties {
    private String domain;
    private String clientId;
    private String clientSecret;
    private String audience;

    // Getters & Setters
    public String getDomain() { return domain; }
    public void setDomain(String domain) { this.domain = domain; }
    public String getClientId() { return clientId; }
    public void setClientId(String clientId) { this.clientId = clientId; }
    public String getClientSecret() { return clientSecret; }
    public void setClientSecret(String clientSecret) { this.clientSecret = clientSecret; }
    public String getAudience() { return audience; }
    public void setAudience(String audience) { this.audience = audience; }
}