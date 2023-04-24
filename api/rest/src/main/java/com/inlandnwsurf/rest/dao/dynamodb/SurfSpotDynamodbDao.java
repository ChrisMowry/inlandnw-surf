package com.inlandnwsurf.rest.dao.dynamodb;


import com.inlandnwsurf.rest.config.DynamoDbProperties;
import com.inlandnwsurf.rest.dao.SurfSpotDao;
import com.inlandnwsurf.rest.model.levels.FlowSource;
import com.inlandnwsurf.rest.model.location.Region;
import com.inlandnwsurf.rest.model.surfspots.SurfSpot;
import com.inlandnwsurf.rest.model.surfspots.SurfSpotLocation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import software.amazon.awssdk.enhanced.dynamodb.DynamoDbEnhancedClient;
import software.amazon.awssdk.enhanced.dynamodb.DynamoDbTable;
import software.amazon.awssdk.enhanced.dynamodb.Key;
import software.amazon.awssdk.enhanced.dynamodb.model.PageIterable;
import software.amazon.awssdk.enhanced.dynamodb.model.QueryConditional;

import java.util.List;
import java.util.stream.Collectors;

import static com.inlandnwsurf.rest.dao.dynamodb.DynamoDbTableSchemas.getSurfSpotAccessDynamoDbRecordTableSchema;

@Service
@Slf4j
// TODO: move service components to Service class
public class SurfSpotDynamodbDao implements SurfSpotDao {

    private final DynamoDbEnhancedClient dynamoDbClient;
    private final DynamoDbProperties dynamoDbProperties;

    @Autowired
    public SurfSpotDynamodbDao(DynamoDbProperties dynamoDbProperties, DynamoDbEnhancedClient dynamoDbClient ){
        this.dynamoDbClient = dynamoDbClient;
        this.dynamoDbProperties = dynamoDbProperties;
    }

    private DynamoDbTable<DynamoDbRecord> getDynamoDbRecordTable(){
        return dynamoDbClient.table( this.dynamoDbProperties.getTable(), getSurfSpotAccessDynamoDbRecordTableSchema());
    }

    /**
     * @param regionId
     * @return
     */
    @Override
    public List<SurfSpot> getSurfSpots(String regionId) {
        regionId = regionId.toUpperCase();

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
                .filter( item -> item.getType().equals(DynamoDbDataType.REGION))
                .map( item -> item.getRegion() )
                .findFirst()
                .orElse(null);

        region.setId(regionId);

        // gets the flow sources associated with the surf spots
        List<FlowSource> flowSources = dynamoDbRecord
                .items().stream()
                .filter( item -> item.getType().equals(DynamoDbDataType.FLOWSOURCE))
                .map(record -> record.getFlowSource())
                .collect(Collectors.toList());


        List<SurfSpot> surfspots = dynamoDbRecord
                .items().stream()
                .filter( item -> item.getType().equals(DynamoDbDataType.SURFSPOT))
                .map( item -> item.getSurfspots() )
                .map(surfspot -> assembleSurfSpot(surfspot, region, flowSources))
                .collect(Collectors.toList());

        return surfspots;
    }

    private static SurfSpot assembleSurfSpot(SurfSpot surfSpot, Region region, List<FlowSource> flowSources){
        surfSpot.setRegion(region);
        List<SurfSpotLocation> surfspotLocations = surfSpot.getSurfspots()
                .stream()
                .map( spot -> {
                    spot.setCurrentFlow(getFlowSource(surfSpot.getFlowSourceId(), flowSources));
                    return spot;
                })
                .collect(Collectors.toList());

        return surfSpot;
    }

    private static FlowSource getFlowSource(String flowSourceName,
                                            List<FlowSource> flowSources){
        long flowSourceId = Long.parseLong( flowSourceName.replaceAll("[^\\d.]", "")  );
        FlowSource flowSource = flowSources.stream()
                .filter( flowsource -> flowsource.getId() == flowSourceId )
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
    public SurfSpot createSurfSpot(String regionId, SurfSpot surfSpot) {
        // TODO: implement this method
        return null;
    }

    /**
     * @param regionId
     * @param surfSpotId
     * @param surfSpot
     */
    @Override
    public SurfSpot updateSurfSpot(String regionId, long surfSpotId, SurfSpot surfSpot) {
        // TODO implement this method
        return null;
    }

    /**
     * @param regionId
     * @param surfSpotId
     */
    @Override
    public SurfSpot deleteSurfSpot(String regionId, long surfSpotId) {
        // TODO implement this method
        return null;
    }

    /**
     * @param regionId
     * @param surfSpotId
     * @return
     */
    @Override
    public List<SurfSpotLocation> getSurfSpotLocations(String regionId, long surfSpotId) {
        SurfSpot spot = getSurfSpot(regionId, surfSpotId);

        // TODO implement this method
        return spot.getSurfspots();
    }

    /**
     * @param regionId
     * @param surfSpotId
     * @param surfSpotLocationId
     * @return
     */
    @Override
    public SurfSpotLocation getSurfSpotLocation(String regionId, long surfSpotId, long surfSpotLocationId) {

        SurfSpotLocation spotLoc = this.getSurfSpotLocations( regionId, surfSpotId )
                .stream()
                .filter( item -> item.getId() == surfSpotLocationId )
                .findFirst().orElse(null);

        return spotLoc;
    }

    /**
     * @param regionId
     * @param surfSpotId
     * @param surfSpotLocation
     */
    @Override
    public SurfSpotLocation addSurfSpotLocation(String regionId, long surfSpotId, SurfSpotLocation surfSpotLocation) {

        // TODO implement this method
        return null;
    }

    /**
     * @param regionId
     * @param surfSpotId
     * @param surfSpotLocationId
     * @param surfSpotLocation
     */
    @Override
    public SurfSpotLocation updateSurfSpotLocation(String regionId,
                                       long surfSpotId,
                                       long surfSpotLocationId,
                                       SurfSpotLocation surfSpotLocation) {

        // TODO implement this method
        return null;

    }

    /**
     * @param regionId
     * @param surfSpotId
     * @param surfSpotLocationId
     */
    @Override
    public SurfSpotLocation deleteSurfSpotLocation(String regionId, long surfSpotId, long surfSpotLocationId) {

        // TODO implement this method
        return null;
    }
}
