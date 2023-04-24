package com.inlandnwsurf.rest.dao.dynamodb;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.inlandnwsurf.rest.dao.dynamodb.DynamoDbDataType;
import com.inlandnwsurf.rest.model.levels.FlowSource;
import com.inlandnwsurf.rest.model.location.Region;
import com.inlandnwsurf.rest.model.surfspots.SurfSpot;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbBean;
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbPartitionKey;
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbSortKey;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DynamoDbRecord {
    private String pk;
    private String sk;
    private DynamoDbDataType type;
    private String flowSourceId;
    private Region region;
    private FlowSource flowSource;
    private SurfSpot surfspots;
}
