package com.marvic.popstats.data;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.config.AbstractMongoClientConfiguration;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

import javax.annotation.Nonnull;
import java.util.Collection;
import java.util.List;

@Configuration
@EnableMongoRepositories
public class MongoConfig extends AbstractMongoClientConfiguration {
    @Override
    @Nonnull
    public MongoClient mongoClient() {
        return MongoClients.create("mongodb://localhost:27017");
    }

    @Override
    @Nonnull
    protected String getDatabaseName() {
        return "census";
    }

    @Override
    @Nonnull
    protected Collection<String> getMappingBasePackages() {
        return List.of("com.marvic.popstats.domain");
    }
}
