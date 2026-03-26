package io.ulysse.ulysseIndepBack.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "appli")
@Getter
@Setter
public class ApplicationConfiguration {


    private Boolean securityDisable=false;



}
