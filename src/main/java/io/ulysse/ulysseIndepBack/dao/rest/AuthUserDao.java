package io.ulysse.ulysseIndepBack.dao.rest;

import io.ulysse.ulysseIndepBack.config.Auth0RestProperties;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

@Component
public class AuthUserDao {

    private final Auth0RestProperties auth0Properties;
    private final RestTemplate restTemplate;

    public AuthUserDao(Auth0RestProperties auth0Properties) {
        this.auth0Properties = auth0Properties;
        this.restTemplate = new RestTemplate();
    }

    // Récupère le token Management API
    private String getManagementToken() {
        String url = auth0Properties.getDomain() + "/oauth/token";

        Map<String, String> body = Map.of(
                "grant_type", "client_credentials",
                "client_id", auth0Properties.getClientId(),
                "client_secret", auth0Properties.getClientSecret(),
                "audience", auth0Properties.getAudience()
        );

        ResponseEntity<Map> response = restTemplate.postForEntity(url, body, Map.class);
        return (String) response.getBody().get("access_token");
    }

    // Récupère les informations d’un utilisateur via l’API Auth0
    public Map<String, Object> getUserById(String userUuid) {
        String token = getManagementToken();

        HttpHeaders headers = new HttpHeaders();
        headers.setBearerAuth(token);
        HttpEntity<Void> entity = new HttpEntity<>(headers);

        String url = auth0Properties.getDomain() + "/api/v2/users/" + userUuid;

        ResponseEntity<Map> response = restTemplate.exchange(
                url,
                HttpMethod.GET,
                entity,
                Map.class
        );

        return response.getBody();
    }

}