package org.dennis.pizzamanagement.database;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.util.Map;

@ConfigurationProperties(prefix = "org.dennis.mongo")
@Data
@Configuration
public class MongoSettingsData {

    private String host;
    private int port;
    private String database;
    private Map<String, String> credentials;


}
