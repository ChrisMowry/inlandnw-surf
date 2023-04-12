package com.inlandnwsurf.rest.config;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import software.amazon.awssdk.auth.credentials.AwsBasicCredentials;
import software.amazon.awssdk.auth.credentials.AwsCredentialsProvider;
import software.amazon.awssdk.auth.credentials.StaticCredentialsProvider;
import software.amazon.awssdk.enhanced.dynamodb.DynamoDbEnhancedClient;
import software.amazon.awssdk.services.dynamodb.DynamoDbClient;

import java.net.URI;
import java.net.URISyntaxException;

@Configuration
@EnableConfigurationProperties(DynamoDbProperties.class)
public class DynamoDBConfig {

    @Autowired
    DynamoDbProperties dynamoDbProperties;

    @Bean
    public DynamoDbClient getDynamoDbClient() {
        AwsCredentialsProvider credentialsProvider =
                StaticCredentialsProvider
                        .create(AwsBasicCredentials.create(
                                dynamoDbProperties.getAccesskey(),
                                dynamoDbProperties.getSecretkey()));

        DynamoDbClient dynamodbClient;

        if ( dynamoDbProperties.isLocal() ){
            dynamodbClient = DynamoDbClient.builder()
                    .credentialsProvider(credentialsProvider)
                    .endpointOverride( dynamoDbProperties.getUri() )
                    .build();
        } else {
            dynamodbClient = DynamoDbClient.builder().build();
        }

        return dynamodbClient;
    }

    @Bean
    public DynamoDbEnhancedClient getDynamoDbEnhancedClient() {
        return DynamoDbEnhancedClient.builder()
                .dynamoDbClient(getDynamoDbClient())
                .build();
    }
}
