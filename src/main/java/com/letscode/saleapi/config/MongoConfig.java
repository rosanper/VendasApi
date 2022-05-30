package com.letscode.saleapi.config;

import com.mongodb.reactivestreams.client.MongoClient;
import com.mongodb.reactivestreams.client.MongoClients;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.config.AbstractReactiveMongoConfiguration;

@Configuration
public class MongoConfig extends AbstractReactiveMongoConfiguration {
    @Override
    protected String getDatabaseName() {
        return "sales";
    }

    @Override
    public MongoClient reactiveMongoClient(){

        return MongoClients.create("mongodb://localhost:27017/sale");
    }
}

