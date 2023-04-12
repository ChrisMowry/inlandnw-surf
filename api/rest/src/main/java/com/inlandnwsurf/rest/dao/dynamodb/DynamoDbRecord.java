package com.inlandnwsurf.rest.dao.dynamodb;

import com.inlandnwsurf.rest.dao.dynamodb.DynamoDbDataType;
import com.inlandnwsurf.rest.model.levels.FlowSource;
import com.inlandnwsurf.rest.model.location.Region;
import com.inlandnwsurf.rest.model.surfspots.SurfSpot;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

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
    private SurfSpot surfspot;
}
