package com.inlandnwsurf.rest.dao;

import com.inlandnwsurf.rest.model.surfspots.SurfSpot;
import com.inlandnwsurf.rest.model.surfspots.SurfSpotLocation;
import software.amazon.awssdk.enhanced.dynamodb.model.PageIterable;

import java.util.ArrayList;
import java.util.List;

public interface SurfSpotDao {

    public PageIterable<SurfSpot> getSurfSpots(String region);
    public SurfSpot getSurfSpot( String region,
                                 long surfSpotId );
    public void createSurfSpot( String region,
                                SurfSpot surfSpot );
    public void updateSurfSpot( String region,
                                long surfSpotId,
                                SurfSpot surfSpot );
    public void deleteSurfSpot( String region,
                                long surfSpotId );
    public List<SurfSpotLocation> getSurfSpotLocations( String region,
                                                        long surfSpotId );
    public SurfSpotLocation getSurfSpotLocation( String region,
                                                 long surfSpotId,
                                                 long surfSpotLocationId );
    public void addSurfSpotLocation( String region,
                                     long surfSpotId,
                                     SurfSpotLocation surfSpotLocation );
    public void updateSurfSpotLocation( String region,
                                        long surfSpotId,
                                        long surfSpotLocationId,
                                        SurfSpotLocation surfSpotLocation );
    public void deleteSurfSpotLocation( String region,
                                        long surfSpotId,
                                        long surfSpotLocationId );

}
