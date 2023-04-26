package com.inlandnwsurf.rest.dao.dynamodb;

import com.inlandnwsurf.rest.config.DynamoDbProperties;
import com.inlandnwsurf.rest.dao.SurfSpotDao;
import com.inlandnwsurf.rest.exception.ElementNotFoundException;
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
     * @param regionId Region containing surf spots.
     * @return List of Surf Spots
     */
    @Override
    public List<SurfSpot> getSurfSpots(String regionId) throws ElementNotFoundException {
        regionId = regionId.toUpperCase();
        log.debug(String.format("Getting surf spots for region %s.", regionId));

        DynamoDbTable<DynamoDbRecord> dynamoDbRecordTable = getDynamoDbRecordTable();

        QueryConditional queryConditional = QueryConditional.keyEqualTo(
                Key.builder()
                        .partitionValue(String.format("REGION#%s", regionId))
                        .build()
        );

        PageIterable<DynamoDbRecord> dynamoDbRecord = dynamoDbRecordTable
                .query(result -> result.queryConditional(queryConditional));

        if ( dynamoDbRecord.items().stream().count() == 0 ){
            throw new ElementNotFoundException(String.format("Region %s Not Found", regionId));
        }

        // gets the region associated with the surf spots
        Region region = dynamoDbRecord
                .items().stream()
                .filter( item -> item.getType().equals(DynamoDbDataType.REGION))
                .map( item -> item.getRegion() )
                .findFirst()
                .orElse(null);

        // gets the flow sources associated with the surf spots
        List<FlowSource> flowSources = dynamoDbRecord
                .items().stream()
                .filter( item -> item.getType().equals(DynamoDbDataType.FLOWSOURCE))
                .map(record -> record.getFlowSource())
                .collect(Collectors.toList());

        // gets surf spot at assembles related objects
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
     * @param regionId Region containing surf spots.
     * @param surfSpotId Id of the surf spot.
     * @return Surf Spot matching Id parameter
     */
    @Override
    public SurfSpot getSurfSpot(String regionId, long surfSpotId) throws ElementNotFoundException {
        log.debug(String.format("Getting surf spot with id %d in region %s", surfSpotId, regionId));

        SurfSpot spot = this.getSurfSpots(regionId)
                .stream()
                .filter( item -> item.getId() == surfSpotId )
                .findFirst().orElse(null);

        if ( spot == null ){
            throw new ElementNotFoundException(String.format("Surfspot with id %d not found.", surfSpotId));
        }
        return spot;
    }

    /**
     * @param regionId Region containing surf spots.
     * @param surfSpot Surf Spot Object
     */
    @Override
    public SurfSpot createSurfSpot(String regionId, SurfSpot surfSpot) {
        // TODO: implement this method
        return null;
    }

    /**
     * @param regionId Region containing surf spots.
     * @param surfSpotId Id of surf spot.
     * @param surfSpot Surf Spot Object
     */
    @Override
    public SurfSpot updateSurfSpot(String regionId, long surfSpotId, SurfSpot surfSpot) {
        // TODO implement this method
        return null;
    }

    /**
     * @param regionId Region containing surf spots.
     * @param surfSpotId Id of surf spot.
     */
    @Override
    public SurfSpot deleteSurfSpot(String regionId, long surfSpotId) {
        // TODO implement this method
        return null;
    }

    /**
     * @param regionId Region containing surf spots.
     * @param surfSpotId Id of surf spot.
     * @return
     */
    @Override
    public List<SurfSpotLocation> getSurfSpotLocations(String regionId, long surfSpotId)
            throws ElementNotFoundException{

        log.debug(String.format("Getting surf spot locations for region %s in surf spot %d",regionId,surfSpotId));
        SurfSpot spot = getSurfSpot(regionId, surfSpotId);

        return spot.getSurfspots();
    }

    /**
     * @param regionId Region containing surf spots.
     * @param surfSpotId Id of surf spot.
     * @param surfSpotLocationId Id of surf spot location.
     * @return
     */
    @Override
    public SurfSpotLocation getSurfSpotLocation(String regionId, long surfSpotId, long surfSpotLocationId)
            throws ElementNotFoundException{

        SurfSpotLocation spotLoc = this.getSurfSpotLocations( regionId, surfSpotId )
                .stream()
                .filter( item -> item.getId() == surfSpotLocationId )
                .findFirst().orElse(null);

        if ( spotLoc == null ) {
            throw new ElementNotFoundException(String.format("Surf spot location with id %d not found.", surfSpotLocationId));
        }

        return spotLoc;
    }

    /**
     * @param regionId Region containing surf spots.
     * @param surfSpotId Id of surf spot.
     * @param surfSpotLocation Surf Spot location object.
     */
    @Override
    public SurfSpotLocation addSurfSpotLocation(String regionId, long surfSpotId, SurfSpotLocation surfSpotLocation) {

        // TODO implement this method
        return null;
    }

    /**
     * @param regionId Region containing surf spots.
     * @param surfSpotId Id of surf spot.
     * @param surfSpotLocationId Id of surf spot location.
     * @param surfSpotLocation Surf Spot location object.
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
     * @param regionId Region containing surf spots.
     * @param surfSpotId Id of surf spot.
     * @param surfSpotLocationId Id of surf spot location.
     */
    @Override
    public SurfSpotLocation deleteSurfSpotLocation(String regionId, long surfSpotId, long surfSpotLocationId) {

        // TODO implement this method
        return null;
    }
}
