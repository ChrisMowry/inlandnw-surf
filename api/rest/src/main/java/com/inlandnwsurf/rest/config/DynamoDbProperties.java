package com.inlandnwsurf.rest.config;

import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.net.URI;
import java.net.URISyntaxException;

@Data
@Component
@ConfigurationProperties("database.dynamodb")
public class DynamoDbProperties {

    @NotNull
    private String table;
    private boolean local;
    private String endpoint;
    private String accesskey;
    private String secretkey;

    public URI getUri(){
        try{
            return new URI( this.endpoint );
        } catch (URISyntaxException ex){
            throw new IllegalArgumentException(ex);
        }
    }
}
