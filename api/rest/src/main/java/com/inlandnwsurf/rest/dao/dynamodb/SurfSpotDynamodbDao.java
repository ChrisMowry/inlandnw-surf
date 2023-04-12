package com.inlandnwsurf.rest.dao.dynamodb;


import com.inlandnwsurf.rest.config.DynamoDbProperties;
import com.inlandnwsurf.rest.dao.SurfSpotDao;
import com.inlandnwsurf.rest.model.surfspots.SurfSpot;
import com.inlandnwsurf.rest.model.surfspots.SurfSpotLocation;
import org.springframework.beans.factory.annotation.Autowired;
import software.amazon.awssdk.enhanced.dynamodb.DynamoDbEnhancedClient;
import software.amazon.awssdk.enhanced.dynamodb.DynamoDbTable;
import software.amazon.awssdk.enhanced.dynamodb.Expression;
import software.amazon.awssdk.enhanced.dynamodb.Key;
import software.amazon.awssdk.enhanced.dynamodb.model.PageIterable;
import software.amazon.awssdk.enhanced.dynamodb.model.QueryConditional;
import software.amazon.awssdk.services.dynamodb.model.AttributeValue;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import static com.inlandnwsurf.rest.dao.dynamodb.DynamoDbTableSchemas.getSurfSpotTableSchema;

public class SurfSpotDynamodbDao implements SurfSpotDao {

    private final DynamoDbEnhancedClient dynamoDbClient;
    private final DynamoDbProperties dynamoDbProperties;

    @Autowired
    public SurfSpotDynamodbDao(DynamoDbProperties dynamoDbProperties, DynamoDbEnhancedClient dynamoDbClient ){
        this.dynamoDbClient = dynamoDbClient;
        this.dynamoDbProperties = dynamoDbProperties;
    }

    private DynamoDbTable<SurfSpot> getSurfSpotTable(){

        DynamoDbTable<SurfSpot> surfSpotTable = dynamoDbClient.table(
                this.dynamoDbProperties.getTable(),
                getSurfSpotTableSchema());

        return surfSpotTable;
    }

    /**
     * @param region
     * @return
     */
    @Override
    public PageIterable<SurfSpot> getSurfSpots(String region) {

        DynamoDbTable<SurfSpot> surfSpotTable = getSurfSpotTable();

        AttributeValue attributeValue = AttributeValue.builder()
                .s("SURFSPOT" ).build();

        Map<String, AttributeValue> expressionValues = new HashMap<>();
        expressionValues.put(":value", attributeValue);

        Expression expression = Expression.builder()
                .expression("type = :value")
                .expressionValues(expressionValues)
                .build();

        QueryConditional queryConditional = QueryConditional.keyEqualTo(
                Key.builder()
                        .partitionValue(String.format("REGION#%s", region))
                        .build()
        );

        PageIterable<SurfSpot> surfspots = surfSpotTable
                .query(result -> result.queryConditional(queryConditional)
                        .filterExpression(expression));

        return surfspots;
    }

    /**
     * @param region
     * @param surfSpotId
     * @return
     */
    @Override
    public SurfSpot getSurfSpot(String region, long surfSpotId) {

        SurfSpot spot = this.getSurfSpots(region)
                .items().stream()
                .filter( item -> item.getId() == surfSpotId )
                .findFirst().orElse(null);

        return spot;
    }

    /**
     * @param region
     * @param surfSpot
     */
    @Override
    public void createSurfSpot(String region, SurfSpot surfSpot) {

    }

    /**
     * @param region
     * @param surfSpotId
     * @param surfSpot
     */
    @Override
    public void updateSurfSpot(String region, long surfSpotId, SurfSpot surfSpot) {

    }

    /**
     * @param region
     * @param surfSpotId
     */
    @Override
    public void deleteSurfSpot(String region, long surfSpotId) {

    }

    /**
     * @param region
     * @param surfSpotId
     * @return
     */
    @Override
    public ArrayList<SurfSpotLocation> getSurfSpotLocations(String region, long surfSpotId) {
        return null;
    }

    /**
     * @param region
     * @param surfSpotId
     * @param surfSpotLocationId
     * @return
     */
    @Override
    public SurfSpotLocation getSurfSpotLocation(String region, long surfSpotId, long surfSpotLocationId) {
        return null;
    }

    /**
     * @param region
     * @param surfSpotId
     * @param surfSpotLocation
     */
    @Override
    public void addSurfSpotLocation(String region, long surfSpotId, SurfSpotLocation surfSpotLocation) {

    }

    /**
     * @param region
     * @param surfSpotId
     * @param surfSpotLocationId
     * @param surfSpotLocation
     */
    @Override
    public void updateSurfSpotLocation(String region, long surfSpotId, long surfSpotLocationId, SurfSpotLocation surfSpotLocation) {

    }

    /**
     * @param region
     * @param surfSpotId
     * @param surfSpotLocationId
     */
    @Override
    public void deleteSurfSpotLocation(String region, long surfSpotId, long surfSpotLocationId) {

    }
}
