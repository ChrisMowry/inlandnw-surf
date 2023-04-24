package com.inlandnwsurf.rest.dao.dynamodb;

import com.inlandnwsurf.rest.config.DynamoDbProperties;
import com.inlandnwsurf.rest.dao.RegionDao;
import com.inlandnwsurf.rest.model.location.Region;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import software.amazon.awssdk.enhanced.dynamodb.DynamoDbEnhancedClient;
import software.amazon.awssdk.enhanced.dynamodb.DynamoDbIndex;
import software.amazon.awssdk.enhanced.dynamodb.Key;
import software.amazon.awssdk.enhanced.dynamodb.model.PageIterable;
import software.amazon.awssdk.enhanced.dynamodb.model.QueryConditional;

import java.util.List;
import java.util.stream.Collectors;

import static com.inlandnwsurf.rest.dao.dynamodb.DynamoDbTableSchemas.getResourceAccessDynamoDbRecordTableSchema;

@Service
public class RegionDynamodbDao implements RegionDao {

    private final DynamoDbEnhancedClient dynamoDbClient;
    private final DynamoDbProperties dynamoDbProperties;

    @Autowired
    public RegionDynamodbDao(DynamoDbProperties dynamoDbProperties,
                             DynamoDbEnhancedClient dynamoDbClient ){
        this.dynamoDbClient = dynamoDbClient;
        this.dynamoDbProperties = dynamoDbProperties;
    }

    private DynamoDbIndex<DynamoDbRecord> getRegionTable(){
        return dynamoDbClient.table( this.dynamoDbProperties.getTable(),
                        getResourceAccessDynamoDbRecordTableSchema())
                .index("region-index");
    }

    /**
     * @return
     */
    @Override
    public List<Region> getRegions() {
        DynamoDbIndex<DynamoDbRecord> dynamoDbRegionTable = getRegionTable();

        QueryConditional queryConditional = QueryConditional.keyEqualTo(
                Key.builder()
                        .partitionValue("REGION")
                        .build()
        );

        PageIterable<DynamoDbRecord> dynamoDbRegions = PageIterable.create(dynamoDbRegionTable
                .query(result -> result.queryConditional(queryConditional)));

        List<Region> regions = dynamoDbRegions
                .items().stream()
                .map( item -> item.getRegion())
                .collect(Collectors.toList());

        return regions;
    }

    /**
     * @param regionId
     * @return
     */
    @Override
    public Region getRegion(String regionId) {

        Region region = this.getRegions()
                .stream()
                .filter( item -> item.getId().equals(regionId.toUpperCase()) )
                .findFirst().orElse(null);

        return region;
    }

    /**
     * @param region
     * @return
     */
    @Override
    public Region createRegion(Region region) {
        return null;
    }

    /**
     * @param region
     * @return
     */
    @Override
    public Region updateRegion(Region region, String regionId) {
        return null;
    }

    /**
     * @param regionId
     * @return
     */
    @Override
    public Region deleteRegion(String regionId) {
        return null;
    }
}
