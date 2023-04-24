package com.inlandnwsurf.rest.dao.dynamodb;

import com.inlandnwsurf.rest.config.DynamoDbProperties;
import com.inlandnwsurf.rest.dao.FlowSourceDao;
import com.inlandnwsurf.rest.model.levels.FlowSource;
import com.inlandnwsurf.rest.model.levels.Gage;
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
public class FlowSourceDynamodbDao implements FlowSourceDao {

    private final DynamoDbEnhancedClient dynamoDbClient;
    private final DynamoDbProperties dynamoDbProperties;

    @Autowired
    public FlowSourceDynamodbDao(DynamoDbProperties dynamoDbProperties,
                                 DynamoDbEnhancedClient dynamoDbClient ){
        this.dynamoDbClient = dynamoDbClient;
        this.dynamoDbProperties = dynamoDbProperties;
    }

    private DynamoDbIndex<DynamoDbRecord> getFlowSourceTable(){
        return dynamoDbClient.table( this.dynamoDbProperties.getTable(),
                        getResourceAccessDynamoDbRecordTableSchema())
                .index("resource-index");
    }
    /**
     * @return
     */
    @Override
    public List<FlowSource> getFlowSources() {
        DynamoDbIndex<DynamoDbRecord> dynamoDbRegionTable = getFlowSourceTable();

        QueryConditional queryConditional = QueryConditional.keyEqualTo(
                Key.builder()
                        .partitionValue("FLOWSOURCE")
                        .build()
        );

        PageIterable<DynamoDbRecord> dynamoDbRegions = PageIterable.create(dynamoDbRegionTable
                .query(result -> result.queryConditional(queryConditional)));

        List<FlowSource> flowSources = dynamoDbRegions
                .items().stream()
                .map( item -> item.getFlowSource())
                .collect(Collectors.toList());

        return flowSources;
    }

    /**
     * @param flowSourceId
     * @return
     */
    @Override
    public FlowSource getFlowSource(long flowSourceId) {
        return null;
    }

    /**
     * @param flowSource
     * @return
     */
    @Override
    public FlowSource createFlowSource(FlowSource flowSource) {
        return null;
    }

    /**
     * @param flowSourceId
     * @param flowSource
     * @return
     */
    @Override
    public FlowSource updateFlowSource(long flowSourceId, FlowSource flowSource) {
        return null;
    }

    /**
     * @param flowSourceId
     * @return
     */
    @Override
    public FlowSource deleteFlowSource(long flowSourceId) {
        return null;
    }

    /**
     * @param flowSourceId
     * @return
     */
    @Override
    public List<Gage> getGages(long flowSourceId) {
        return null;
    }

    /**
     * @param flowSourceId
     * @param gageId
     * @return
     */
    @Override
    public Gage getGage(long flowSourceId, String gageId) {
        return null;
    }

    /**
     * @param flowSourceId
     * @param GageId
     * @return
     */
    @Override
    public Gage createGage(long flowSourceId, String GageId) {
        return null;
    }

    /**
     * @param flowSourceId
     * @param gageId
     * @return
     */
    @Override
    public Gage updateGage(long flowSourceId, String gageId) {
        return null;
    }

    /**
     * @param flowSourceId
     * @param gageId
     * @return
     */
    @Override
    public Gage deleteGage(long flowSourceId, String gageId) {
        return null;
    }
}
