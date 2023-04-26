package com.inlandnwsurf.rest.dao.dynamodb;

import com.inlandnwsurf.rest.model.levels.*;
import com.inlandnwsurf.rest.model.location.Coordinates;
import com.inlandnwsurf.rest.model.location.Location;
import com.inlandnwsurf.rest.model.location.Region;
import com.inlandnwsurf.rest.model.media.Link;
import com.inlandnwsurf.rest.model.media.Media;
import com.inlandnwsurf.rest.model.media.Section;
import com.inlandnwsurf.rest.model.surfspots.Difficulty;
import com.inlandnwsurf.rest.model.surfspots.SurfSpot;
import com.inlandnwsurf.rest.model.surfspots.SurfSpotLocation;
import software.amazon.awssdk.enhanced.dynamodb.EnhancedType;
import software.amazon.awssdk.enhanced.dynamodb.TableSchema;
import software.amazon.awssdk.enhanced.dynamodb.internal.converter.attribute.EnumAttributeConverter;

import java.net.URL;
import java.time.Instant;

import static software.amazon.awssdk.enhanced.dynamodb.mapper.StaticAttributeTags.*;

public final class DynamoDbTableSchemas {

    public static TableSchema<DynamoDbRecord> getResourceAccessDynamoDbRecordTableSchema(){
        return TableSchema.builder(DynamoDbRecord.class)
                .newItemSupplier( DynamoDbRecord::new )
                .addAttribute( DynamoDbDataType.class, a -> a.name("rowtype")
                        .getter( DynamoDbRecord::getType )
                        .setter( DynamoDbRecord::setType )
                        .attributeConverter(EnumAttributeConverter.create( DynamoDbDataType.class ))
                        .tags(secondaryPartitionKey("resource-index")))
                .addAttribute( String.class, a -> a.name("pk")
                        .getter( DynamoDbRecord::getPk )
                        .setter( DynamoDbRecord::setPk )
                        .tags(secondarySortKey("resource-index")))
                .addAttribute( String.class, a -> a.name("sk")
                        .getter( DynamoDbRecord::getSk )
                        .setter( DynamoDbRecord::setSk ))
                .addAttribute( String.class, a -> a.name("flow-source-id")
                        .getter( DynamoDbRecord::getFlowSourceId )
                        .setter( DynamoDbRecord::setFlowSourceId ))
                .addAttribute( EnhancedType.documentOf(Region.class,
                        getRegionTableSchema()), a -> a.name("region")
                        .getter( DynamoDbRecord::getRegion )
                        .setter( DynamoDbRecord::setRegion ))
                .addAttribute( EnhancedType.documentOf(FlowSource.class,
                        getFlowSourceTableSchema()), a -> a.name("flow-source")
                        .getter( DynamoDbRecord::getFlowSource )
                        .setter( DynamoDbRecord::setFlowSource ))
                .addAttribute( EnhancedType.documentOf(SurfSpot.class,
                        getSurfSpotTableSchema()), a -> a.name("surf-spot")
                        .getter( DynamoDbRecord::getSurfspots )
                        .setter( DynamoDbRecord::setSurfspots ))
                .build();
    }

    public static TableSchema<DynamoDbRecord> getSurfSpotAccessDynamoDbRecordTableSchema(){
        return TableSchema.builder(DynamoDbRecord.class)
                .newItemSupplier( DynamoDbRecord::new )
                .addAttribute( String.class, a -> a.name("pk")
                        .getter( DynamoDbRecord::getPk )
                        .setter( DynamoDbRecord::setPk )
                        .tags(primaryPartitionKey()))
                .addAttribute( String.class, a -> a.name("sk")
                        .getter( DynamoDbRecord::getSk )
                        .setter( DynamoDbRecord::setSk )
                        .tags(primarySortKey()))
                .addAttribute( DynamoDbDataType.class, a -> a.name("rowtype")
                        .getter( DynamoDbRecord::getType )
                        .setter( DynamoDbRecord::setType )
                        .attributeConverter(EnumAttributeConverter.create( DynamoDbDataType.class )))
                .addAttribute( String.class, a -> a.name("flow-source-id")
                        .getter( DynamoDbRecord::getFlowSourceId )
                        .setter( DynamoDbRecord::setFlowSourceId ))
                .addAttribute( EnhancedType.documentOf(Region.class,
                        getRegionTableSchema()), a -> a.name("region")
                        .getter( DynamoDbRecord::getRegion )
                        .setter( DynamoDbRecord::setRegion ))
                .addAttribute( EnhancedType.documentOf(FlowSource.class,
                        getFlowSourceTableSchema()), a -> a.name("flow-source")
                        .getter( DynamoDbRecord::getFlowSource )
                        .setter( DynamoDbRecord::setFlowSource ))
                .addAttribute( EnhancedType.documentOf(SurfSpot.class,
                        getSurfSpotTableSchema()), a -> a.name("surf-spot")
                        .getter( DynamoDbRecord::getSurfspots )
                        .setter( DynamoDbRecord::setSurfspots ))
                .build();
    }

    public static TableSchema<Region> getRegionTableSchema(){
        return TableSchema.builder(Region.class)
                .newItemSupplier( Region::new )
                .addAttribute( String.class, a -> a.name("id")
                        .getter( Region::getId )
                        .setter( Region::setId ))
                .addAttribute( String.class, a -> a.name("name")
                        .getter( Region::getName )
                        .setter( Region::setName ))
                .addAttribute( EnhancedType.listOf(EnhancedType.documentOf(Coordinates.class,
                        getCoordinatesTableSchema())), a -> a.name("boundary")
                        .getter( Region::getBoundary )
                        .setter( Region::setBoundary ))
                .addAttribute( String.class, a -> a.name("created-by")
                        .getter( Region::getCreatedBy )
                        .setter( Region::setCreatedBy ))
                .addAttribute( Instant.class, a -> a.name("created-on")
                        .getter( Region::getCreatedOn )
                        .setter( Region::setCreatedOn ))
                .addAttribute( String.class, a -> a.name("modified-by")
                        .getter( Region::getModifiedBy )
                        .setter( Region::setModifiedBy ))
                .addAttribute( Instant.class, a -> a.name("modified-on")
                        .getter( Region::getModifiedOn )
                        .setter( Region::setModifiedOn ))
                .build();
    }

    public static TableSchema<FlowSource> getFlowSourceTableSchema() {
        return TableSchema.builder(FlowSource.class)
                .newItemSupplier( FlowSource::new )
                .addAttribute( long.class, a -> a.name("id")
                        .getter( FlowSource::getId )
                        .setter( FlowSource::setId ))
                .addAttribute( EnhancedType.documentOf( Flow.class,
                        getFlowTableSchema()), a -> a.name("flow")
                        .getter( FlowSource::getFlow )
                        .setter( FlowSource::setFlow ))
                .addAttribute( EnhancedType.listOf(EnhancedType.documentOf(Gage.class,
                        getGageTableSchema())), a -> a.name("gages")
                        .getter( FlowSource::getGages )
                        .setter( FlowSource::setGages ))
                .addAttribute( String.class, a -> a.name("gage-expression")
                        .getter( FlowSource::getGageExpression )
                        .setter( FlowSource::setGageExpression ))
                .addAttribute( String.class, a -> a.name("created-by")
                        .getter( FlowSource::getCreatedBy )
                        .setter( FlowSource::setCreatedBy ))
                .addAttribute( Instant.class, a -> a.name("created-on")
                        .getter( FlowSource::getCreatedOn )
                        .setter( FlowSource::setCreatedOn ))
                .addAttribute( String.class, a -> a.name("modified-by")
                        .getter( FlowSource::getModifiedBy )
                        .setter( FlowSource::setModifiedBy ))
                .addAttribute( Instant.class, a -> a.name("modified-on")
                        .getter( FlowSource::getModifiedOn )
                        .setter( FlowSource::setModifiedOn ))
                .build();
    }

    public static TableSchema<Gage> getGageTableSchema(){
        return TableSchema.builder(Gage.class)
                .newItemSupplier( Gage::new )
                .addAttribute( String.class, a -> a.name("id")
                        .getter( Gage::getId )
                        .setter( Gage::setId ))
                .addAttribute( String.class, a -> a.name("name")
                        .getter( Gage::getName )
                        .setter( Gage::setName ))
                .addAttribute( EnhancedType.listOf(EnhancedType.documentOf(GageValueRecord.class,
                        getGageValueRecordTableSchema())), a -> a.name("values")
                        .getter( Gage::getValues )
                        .setter( Gage::setValues ))
                .addAttribute( EnhancedType.listOf(EnhancedType.documentOf(DailyGageRange.class,
                        getDailyGageRangeTableSchema())), a -> a.name("history")
                        .getter( Gage::getHistory )
                        .setter( Gage::setHistory ))
                .addAttribute( EnhancedType.documentOf( Location.class,
                        getLocationTableSchema()), a -> a.name("location")
                        .getter( Gage::getLocation )
                        .setter( Gage::setLocation ))
                .addAttribute( URL.class, a -> a.name("url")
                        .getter( Gage::getUrl )
                        .setter( Gage::setUrl ))
                .build();
    }

    public static TableSchema<DailyGageRange> getDailyGageRangeTableSchema(){
        return TableSchema.builder(DailyGageRange.class)
                .addAttribute( String.class, a -> a.name("date")
                        .getter( DailyGageRange::getDate )
                        .setter( DailyGageRange::setDate ))
                .addAttribute( EnhancedType.documentOf(GageValue.class,
                        getGageValueTableSchema()), a -> a.name("min-daily-flow")
                        .getter( DailyGageRange::getMinGageFlow )
                        .setter( DailyGageRange::setMinGageFlow ))
                .addAttribute( EnhancedType.documentOf(GageValue.class,
                        getGageValueTableSchema()), a -> a.name("max-daily-flow")
                        .getter( DailyGageRange::getMaxGageFlow )
                        .setter( DailyGageRange::setMaxGageFlow ))
                .build();
    }

    public static TableSchema<GageValueRecord> getGageValueRecordTableSchema(){
        return TableSchema.builder(GageValueRecord.class)
                .newItemSupplier( GageValueRecord::new )
                .addAttribute( EnhancedType.documentOf(GageValue.class,
                        getGageValueTableSchema()), a -> a.name("value")
                        .getter( GageValueRecord::getValue )
                        .setter( GageValueRecord::setValue ))
                .addAttribute( Instant.class, a -> a.name("timestamp")
                        .getter( GageValueRecord::getTimestamp )
                        .setter( GageValueRecord::setTimestamp ))
                .build();
    }

    public static TableSchema<GageValue> getGageValueTableSchema(){
        return TableSchema.builder(GageValue.class)
                .newItemSupplier( GageValue::new )
                .addAttribute( double.class, a -> a.name("gage-hgt-value")
                        .getter( GageValue::getGageHeightValue )
                        .setter( GageValue::setGageHeightValue ))
                .addAttribute( Unit.class, a -> a.name("gage-hgt-unit")
                        .getter( GageValue::getGageHeightUnit )
                        .setter( GageValue::setGageHeightUnit )
                        .attributeConverter(EnumAttributeConverter.create( Unit.class )))
                .addAttribute( double.class, a -> a.name("discharge-value")
                        .getter( GageValue::getDischargeValue )
                        .setter( GageValue::setDischargeValue ))
                .addAttribute( Unit.class, a -> a.name("discharge-unit")
                        .getter( GageValue::getDischargeUnit )
                        .setter( GageValue::setDischargeUnit )
                        .attributeConverter(EnumAttributeConverter.create( Unit.class )))
                .build();
    }

    public static TableSchema<SurfSpot> getSurfSpotTableSchema(){
        return TableSchema.builder(SurfSpot.class)
                .newItemSupplier( SurfSpot::new )
                .addAttribute( long.class, a -> a.name("id")
                        .getter( SurfSpot::getId )
                        .setter( SurfSpot::setId ))
                .addAttribute( String.class, a -> a.name("flow-source-id")
                        .getter( SurfSpot::getFlowSourceId )
                        .setter( SurfSpot::setFlowSourceId ))
                .addAttribute( String.class, a -> a.name("name")
                        .getter( SurfSpot::getName )
                        .setter( SurfSpot::setName ))
                .addAttribute( EnhancedType.listOf(EnhancedType.documentOf(SurfSpotLocation.class,
                        getSurfSpotLocationTableSchema())), a -> a.name("surf-spots")
                        .getter( SurfSpot::getSurfspots )
                        .setter( SurfSpot::setSurfspots ))
                .addAttribute( EnhancedType.documentOf(Location.class,
                        getLocationTableSchema()), a -> a.name("location")
                        .getter( SurfSpot::getLocation )
                        .setter( SurfSpot::setLocation ))
                .addAttribute( String.class, a -> a.name("created-by")
                        .getter( SurfSpot::getCreatedBy )
                        .setter( SurfSpot::setCreatedBy ))
                .addAttribute( Instant.class, a -> a.name("created-on")
                        .getter( SurfSpot::getCreatedOn )
                        .setter( SurfSpot::setCreatedOn ))
                .addAttribute( String.class, a -> a.name("modified-by")
                        .getter( SurfSpot::getModifiedBy )
                        .setter( SurfSpot::setModifiedBy ))
                .addAttribute( Instant.class, a -> a.name("modified-on")
                        .getter( SurfSpot::getModifiedOn )
                        .setter( SurfSpot::setModifiedOn ))
                .build();
    }

    public static TableSchema<SurfSpotLocation> getSurfSpotLocationTableSchema(){
        return TableSchema.builder(SurfSpotLocation.class)
                .newItemSupplier(SurfSpotLocation::new)
                .addAttribute( long.class, a -> a.name("id")
                        .getter( SurfSpotLocation::getId )
                        .setter( SurfSpotLocation::setId ))
                .addAttribute( String.class, a -> a.name("name")
                        .getter( SurfSpotLocation::getName )
                        .setter( SurfSpotLocation::setName ))
                .addAttribute( EnhancedType.listOf(EnhancedType.documentOf(Section.class,
                        getSectionTableSchema())), a -> a.name("description")
                        .getter( SurfSpotLocation::getDescription )
                        .setter( SurfSpotLocation::setDescription ))
                .addAttribute( Difficulty.class, a -> a.name("difficulty")
                        .getter( SurfSpotLocation::getDifficulty )
                        .setter( SurfSpotLocation::setDifficulty )
                        .attributeConverter(EnumAttributeConverter.create( Difficulty.class )))
                .addAttribute( String.class, a -> a.name("tricks")
                        .getter( SurfSpotLocation::getTricks )
                        .setter( SurfSpotLocation::setTricks ))
                .addAttribute( String.class, a -> a.name("features")
                        .getter( SurfSpotLocation::getFeatures )
                        .setter( SurfSpotLocation::setFeatures ))
                .addAttribute( EnhancedType.documentOf( Location.class,
                        getLocationTableSchema()), a -> a.name("location")
                        .getter( SurfSpotLocation::getLocation )
                        .setter( SurfSpotLocation::setLocation ))
                .addAttribute( EnhancedType.documentOf( Location.class,
                        getLocationTableSchema()), a -> a.name("access")
                        .getter( SurfSpotLocation::getAccess )
                        .setter( SurfSpotLocation::setAccess ))
                .addAttribute( EnhancedType.documentOf( FlowRange.class,
                        getFlowRangeTableSchema()), a -> a.name("flow-range")
                        .getter( SurfSpotLocation::getFlowRange )
                        .setter( SurfSpotLocation::setFlowRange ))
                .addAttribute( EnhancedType.documentOf( FlowRange.class,
                        getFlowRangeTableSchema()), a -> a.name("optimum-flow-range")
                        .getter( SurfSpotLocation::getOptimumFlowRange )
                        .setter( SurfSpotLocation::setOptimumFlowRange ))
                .addAttribute( EnhancedType.listOf(EnhancedType.documentOf(FlowInfo.class,
                        getFlowInfoTableSchema())), a -> a.name("flow-info")
                        .getter( SurfSpotLocation::getFlowInfo )
                        .setter( SurfSpotLocation::setFlowInfo ))
                .addAttribute( EnhancedType.listOf(EnhancedType.documentOf(Link.class,
                        getLinkTableSchema())), a -> a.name("links")
                        .getter( SurfSpotLocation::getLinks )
                        .setter( SurfSpotLocation::setLinks ))
                .addAttribute( EnhancedType.listOf(EnhancedType.documentOf(Media.class,
                        getMediaTableSchema())), a -> a.name("images")
                        .getter( SurfSpotLocation::getImages )
                        .setter( SurfSpotLocation::setImages ))
                .addAttribute( EnhancedType.listOf(EnhancedType.documentOf(Media.class,
                        getMediaTableSchema())), a -> a.name("videos")
                        .getter( SurfSpotLocation::getVideos )
                        .setter( SurfSpotLocation::setVideos ))
                .addAttribute( EnhancedType.listOf(EnhancedType.documentOf(HistoryValue.class,
                        getHistoryValueTableSchema())), a -> a.name("history")
                        .getter( SurfSpotLocation::getHistory )
                        .setter( SurfSpotLocation::setHistory ))
                .addAttribute( String.class, a -> a.name("created-by")
                        .getter( SurfSpotLocation::getCreatedBy )
                        .setter( SurfSpotLocation::setCreatedBy ))
                .addAttribute( Instant.class, a -> a.name("created-on")
                        .getter( SurfSpotLocation::getCreatedOn )
                        .setter( SurfSpotLocation::setCreatedOn ))
                .addAttribute( String.class, a -> a.name("modified-by")
                        .getter( SurfSpotLocation::getModifiedBy )
                        .setter( SurfSpotLocation::setModifiedBy ))
                .addAttribute( Instant.class, a -> a.name("modified-on")
                        .getter( SurfSpotLocation::getModifiedOn )
                        .setter( SurfSpotLocation::setModifiedOn ))
                .build();
    }

    public static TableSchema<FlowRange> getFlowRangeTableSchema(){
        return TableSchema.builder(FlowRange.class)
                .newItemSupplier(FlowRange::new)
                .addAttribute( EnhancedType.documentOf(Flow.class,
                        getFlowTableSchema()), a -> a.name("min-flow")
                        .getter( FlowRange::getMinFlow )
                        .setter( FlowRange::setMinFlow ))
                .addAttribute( EnhancedType.documentOf(Flow.class,
                        getFlowTableSchema()), a -> a.name("max-flow")
                        .getter( FlowRange::getMaxFlow )
                        .setter( FlowRange::setMaxFlow ))
                .build();
    }

    public static TableSchema<Flow> getFlowTableSchema(){
        return TableSchema.builder(Flow.class)
                .newItemSupplier( Flow::new )
                .addAttribute( double.class, a -> a.name("value")
                        .getter( Flow::getValue )
                        .setter( Flow::setValue ))
                .addAttribute( Unit.class, a -> a.name("unit")
                        .getter( Flow::getUnit )
                        .setter( Flow::setUnit )
                        .attributeConverter(EnumAttributeConverter.create(Unit.class)))
                .addAttribute( FlowType.class, a -> a.name("flow-type")
                        .getter( Flow::getFlowType )
                        .setter( Flow::setFlowType )
                        .attributeConverter(EnumAttributeConverter.create(FlowType.class)))
                .build();
    }

    public static TableSchema<Section> getSectionTableSchema(){
        return TableSchema.builder(Section.class)
                .newItemSupplier( Section::new )
                .addAttribute( int.class, a -> a.name("order")
                        .getter( Section::getOrder )
                        .setter( Section::setOrder ))
                .addAttribute( String.class, a -> a.name("heading")
                        .getter( Section::getHeading )
                        .setter( Section::setHeading ))
                .addAttribute( String.class, a -> a.name("paragraph")
                        .getter( Section::getParagraph )
                        .setter( Section::setParagraph ))
                .build();
    }

    public static TableSchema<Location> getLocationTableSchema(){
        return TableSchema.builder(Location.class)
                .newItemSupplier( Location::new )
                .addAttribute( String.class, a -> a.name("state")
                        .getter(Location::getState )
                        .setter( Location::setState ))
                .addAttribute( EnhancedType.documentOf(Coordinates.class,
                        getCoordinatesTableSchema()), a -> a.name("coordinates")
                        .getter(Location::getCoordinates )
                        .setter( Location::setCoordinates ))
                .build();
    }

    public static TableSchema<Coordinates> getCoordinatesTableSchema(){
        return TableSchema.builder(Coordinates.class)
                .newItemSupplier( Coordinates::new )
                .addAttribute( String.class, a -> a.name("espg")
                        .getter(Coordinates::getEspg )
                        .setter( Coordinates::setEspg ))
                .addAttribute( float.class, a -> a.name("lat")
                        .getter(Coordinates::getLat )
                        .setter( Coordinates::setLat ))
                .addAttribute( float.class, a -> a.name("lng")
                        .getter(Coordinates::getLng )
                        .setter( Coordinates::setLng ))
                .build();
    }

    public static TableSchema<FlowInfo> getFlowInfoTableSchema(){
        return TableSchema.builder(FlowInfo.class)
                .newItemSupplier( FlowInfo::new )
                .addAttribute( int.class, a -> a.name("order")
                        .getter( FlowInfo::getOrder )
                        .setter( FlowInfo::setOrder ))
                .addAttribute( EnhancedType.documentOf( FlowRange.class,
                        getFlowRangeTableSchema()), a -> a.name("flow-range")
                        .getter( FlowInfo::getFlowRange )
                        .setter( FlowInfo::setFlowRange ))
                .addAttribute( String.class, a -> a.name("description")
                        .getter( FlowInfo::getDescription )
                        .setter( FlowInfo::setDescription ))
                .build();
    }

    public static TableSchema<Link> getLinkTableSchema(){
        return TableSchema.builder(Link.class)
                .newItemSupplier( Link::new )
                .addAttribute( int.class, a -> a.name("order")
                        .getter( Link::getOrder )
                        .setter( Link::setOrder ))
                .addAttribute( String.class, a -> a.name("label")
                        .getter( Link::getLabel )
                        .setter( Link::setLabel ))
                .addAttribute( String.class, a -> a.name("url")
                        .getter( Link::getUrl )
                        .setter( Link::setUrl ))
                .build();
    }

    public static TableSchema<Media> getMediaTableSchema(){
        return TableSchema.builder(Media.class)
                .newItemSupplier( Media::new )
                .addAttribute( int.class, a -> a.name("order")
                        .getter( Media::getOrder )
                        .setter( Media::setOrder ))
                .addAttribute( String.class, a -> a.name("name")
                        .getter( Media::getName )
                        .setter( Media::setName ))
                .addAttribute( String.class, a -> a.name("url")
                        .getter( Media::getUrl )
                        .setter( Media::setUrl ))
                .build();
    }

    public static TableSchema<HistoryValue> getHistoryValueTableSchema(){
        return TableSchema.builder(HistoryValue.class)
                .newItemSupplier( HistoryValue::new )
                .addAttribute( int.class, a -> a.name("month")
                        .getter( HistoryValue::getMonth )
                        .setter( HistoryValue::setMonth ))
                .addAttribute( int.class, a -> a.name("day")
                        .getter( HistoryValue::getDay )
                        .setter( HistoryValue::setDay ))
                .addAttribute( double.class, a -> a.name("percent")
                        .getter( HistoryValue::getPercent )
                        .setter( HistoryValue::setPercent ))
                .build();
    }
}
