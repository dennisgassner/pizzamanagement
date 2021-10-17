package org.dennis.pizzamanagement.database;

import com.mongodb.ConnectionString;
import com.mongodb.MongoClientSettings;
import com.mongodb.MongoCredential;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.core.MongoTemplate;
import java.util.Map;


@Configuration
public class MongoClientConfiguration  {

    @Autowired
    MongoSettingsData mongoSettingsData;

    public @Bean MongoClient mongoClient() {
        Map<String, String> credentials = mongoSettingsData.getCredentials();
        return MongoClients.create(MongoClientSettings.builder().credential(MongoCredential.createCredential(credentials.get("user"),credentials.get("database"),credentials.get("password").toCharArray()))
                .applyConnectionString(new ConnectionString("mongodb://"+mongoSettingsData.getHost()+":"+mongoSettingsData.getPort()))
                .build());
    }

    public @Bean MongoTemplate mongoTemplate() {
        return new MongoTemplate(mongoClient(), mongoSettingsData.getDatabase());
    }

}
