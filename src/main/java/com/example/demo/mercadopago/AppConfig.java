package com.example.demo.mercadopago;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "mercadopago")
public class AppConfig {

    private String accessToken;

    public String getAccessToken() {
        return accessToken;
    }

    // El nombre del método corregido con dos 's'
    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }
}