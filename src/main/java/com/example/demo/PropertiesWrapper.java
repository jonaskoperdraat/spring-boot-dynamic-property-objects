package com.example.demo;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

@Configuration
@ConfigurationProperties(prefix = PropertiesWrapper.PREFIX)
@Data
class PropertiesWrapper {

    public static final String PREFIX = "my";

    private Map<String, CustomProperties> properties = new HashMap<>();

}
