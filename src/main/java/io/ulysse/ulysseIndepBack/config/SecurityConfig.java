package io.ulysse.ulysseIndepBack.config;

import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

import java.util.logging.Logger;

import static org.springframework.security.config.Customizer.withDefaults;

/**
 * Configures our application with Spring Security to restrict access to our API endpoints.
 */
@Configuration
@SecurityScheme(
        name = "BearerAuthentication",
        type = SecuritySchemeType.HTTP,
        bearerFormat = "JWT",
        scheme = "bearer"
)
public class SecurityConfig {
    private static final java.util.logging.Logger LOGGER = Logger.getLogger(SecurityConfig.class.getName());
    @Autowired
    ApplicationConfiguration applicationConfiguration;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {


    /*
    This is where we configure the security required for our endpoints and setup our app to serve as
    an OAuth2 Resource Server, using JWT validation.
    */
        if (applicationConfiguration.getSecurityDisable()){
            LOGGER.info("Security Disabled Security : "+applicationConfiguration.getSecurityDisable().toString());
            return http
                    .authorizeHttpRequests((authorize) -> authorize
                            .requestMatchers("/**").permitAll()
                    )
                    .cors(withDefaults())
                    .oauth2ResourceServer(oauth2 -> oauth2
                            .jwt(jwt->{})
                    )

                    .build();
        }
        return http
                .authorizeHttpRequests((authorize) -> authorize
                        .requestMatchers("/api/public").permitAll()
                        .requestMatchers("/api/private").authenticated()
                        .requestMatchers("swagger-ui/**").permitAll()
                        .requestMatchers("v3/**").permitAll()
                        .requestMatchers("/user/**").authenticated() // protéger ce endpoint
                        .requestMatchers("/api/private-scoped").hasAuthority("SCOPE_read:messages")
                )
                .cors(withDefaults())
                .oauth2ResourceServer(oauth2 -> oauth2
                        .jwt(jwt->{})
                )
                .build();
    }
}