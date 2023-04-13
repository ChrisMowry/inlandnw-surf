package com.inlandnwsurf.rest.dao.dynamodb;


import com.inlandnwsurf.rest.config.DynamoDbProperties;
import com.inlandnwsurf.rest.dao.SurfSpotDao;
import com.inlandnwsurf.rest.model.levels.FlowSource;
import com.inlandnwsurf.rest.model.location.Region;
import com.inlandnwsurf.rest.model.surfspots.SurfSpot;
import com.inlandnwsurf.rest.model.surfspots.SurfSpotLocation;
import org.springframework.beans.factory.annotation.Autowired;
import software.amazon.awssdk.enhanced.dynamodb.DynamoDbEnhancedClient;
import software.amazon.awssdk.enhanced.dynamodb.DynamoDbTable;
import software.amazon.awssdk.enhanced.dynamodb.Key;
import software.amazon.awssdk.enhanced.dynamodb.model.PageIterable;
import software.amazon.awssdk.enhanced.dynamodb.model.QueryConditional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static com.inlandnwsurf.rest.dao.dynamodb.DynamoDbTableSchemas.getDynamoDbRecordTableSchema;

public class SurfSpotDynamodbDao implements SurfSpotDao {

    private final DynamoDbEnhancedClient dynamoDbClient;
    private final DynamoDbProperties dynamoDbProperties;

    @Autowired
    public SurfSpotDynamodbDao(DynamoDbProperties dynamoDbProperties, DynamoDbEnhancedClient dynamoDbClient ){
        this.dynamoDbClient = dynamoDbClient;
        this.dynamoDbProperties = dynamoDbProperties;
    }

    private DynamoDbTable<DynamoDbRecord> getDynamoDbRecordTable(){

        DynamoDbTable<DynamoDbRecord> dynamoDbRecordTable = dynamoDbClient.table(
                this.dynamoDbProperties.getTable(),
                getDynamoDbRecordTableSchema());

        return dynamoDbRecordTable;
    }

    /**
     * @param regionId
     * @return
     */
    @Override
    public List<SurfSpot> getSurfSpots(String regionId) {

        DynamoDbTable<DynamoDbRecord> dynamoDbRecordTable = getDynamoDbRecordTable();

        QueryConditional queryConditional = QueryConditional.keyEqualTo(
                Key.builder()
                        .partitionValue(String.format("REGION#%s", regionId))
                        .build()
        );

        PageIterable<DynamoDbRecord> dynamoDbRecord = dynamoDbRecordTable
                .query(result -> result.queryConditional(queryConditional));

        // gets the region associated with the surf spots
        Region region = dynamoDbRecord
                .items().stream()
                .filter( item -> item.getType().equals("REGION"))
                .map( item -> item.getRegion() )
                .findFirst()
                .orElse(null);

        // gets the flow sources associated with the surf spots
        List<FlowSource> flowSources = dynamoDbRecord
                .items().stream()
                .filter( item -> item.getType().equals("FLOWSOURCE"))
                .map(record -> record.getFlowSource())
                .collect(Collectors.toList());

        List<SurfSpot> surfspots = dynamoDbRecord
                .items().stream()
                .filter( item -> item.getType().equals("SURFSPOT"))
                .map( item -> assembleSurfSpot(item.getSurfspot(), region, flowSources ) )
                .collect(Collectors.toList());

        return surfspots;
    }

    private static SurfSpot assembleSurfSpot(SurfSpot surfSpot, Region region, List<FlowSource> flowSources){
        surfSpot.setRegion(region);
        List<SurfSpotLocation> surfspotLocations = surfSpot.getSurfspots()
                .stream()
                .map( spot -> {
                    spot.setCurrentFlow(getFlowSource(spot, flowSources));
                    return spot;
                })
                .collect(Collectors.toList());
        return surfSpot;
    }

    private static FlowSource getFlowSource(SurfSpotLocation surfspotLocation,
                                            List<FlowSource> flowSources){
        String flowSourceName = surfspotLocation.getFlowSourceName();
        long flowSourceId = Long.parseLong(flowSourceName.substring(flowSourceName.lastIndexOf("#")) + 1);
        FlowSource flowSource = flowSources.stream()
                .filter( flowsource -> flowsource.getId() == flowSourceId)
                .findFirst()
                .orElse(null);

        return flowSource;
    }



    /**
     * @param regionId
     * @param surfSpotId
     * @return
     */
    @Override
    public SurfSpot getSurfSpot(String regionId, long surfSpotId) {

        SurfSpot spot = this.getSurfSpots(regionId)
                .stream()
                .filter( item -> item.getId() == surfSpotId )
                .findFirst().orElse(null);

        return spot;
    }

    /**
     * @param regionId
     * @param surfSpot
     */
    @Override
    public void createSurfSpot(String regionId, SurfSpot surfSpot) {

    }

    /**
     * @param regionId
     * @param surfSpotId
     * @param surfSpot
     */
    @Override
    public void updateSurfSpot(String regionId, long surfSpotId, SurfSpot surfSpot) {

    }

    /**
     * @param regionId
     * @param surfSpotId
     */
    @Override
    public void deleteSurfSpot(String regionId, long surfSpotId) {

    }

    /**
     * @param regionId
     * @param surfSpotId
     * @return
     */
    @Override
    public ArrayList<SurfSpotLocation> getSurfSpotLocations(String regionId, long surfSpotId) {
        return null;
    }

    /**
     * @param regionId
     * @param surfSpotId
     * @param surfSpotLocationId
     * @return
     */
    @Override
    public SurfSpotLocation getSurfSpotLocation(String regionId, long surfSpotId, long surfSpotLocationId) {
        return null;
    }

    /**
     * @param regionId
     * @param surfSpotId
     * @param surfSpotLocation
     */
    @Override
    public void addSurfSpotLocation(String regionId, long surfSpotId, SurfSpotLocation surfSpotLocation) {

    }

    /**
     * @param regionId
     * @param surfSpotId
     * @param surfSpotLocationId
     * @param surfSpotLocation
     */
    @Override
    public void updateSurfSpotLocation(String regionId, long surfSpotId, long surfSpotLocationId, SurfSpotLocation surfSpotLocation) {

    }

    /**
     * @param regionId
     * @param surfSpotId
     * @param surfSpotLocationId
     */
    @Override
    public void deleteSurfSpotLocation(String regionId, long surfSpotId, long surfSpotLocationId) {

    }
}
