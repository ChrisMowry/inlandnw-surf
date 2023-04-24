package com.inlandnwsurf.rest.dao;

import com.inlandnwsurf.rest.model.surfspots.SurfSpot;
import com.inlandnwsurf.rest.model.surfspots.SurfSpotLocation;

import java.util.List;

public interface SurfSpotDao {

    public List<SurfSpot> getSurfSpots(String regionId);
    public SurfSpot getSurfSpot( String regionId,
                                 long surfSpotId );
    public SurfSpot createSurfSpot( String regionId,
                                SurfSpot surfSpot );
    public SurfSpot updateSurfSpot( String regionId,
                                long surfSpotId,
                                SurfSpot surfSpot );
    public SurfSpot deleteSurfSpot( String regionId,
                                long surfSpotId );
    public List<SurfSpotLocation> getSurfSpotLocations( String regionId,
                                                        long surfSpotId );
    public SurfSpotLocation getSurfSpotLocation( String regionId,
                                                 long surfSpotId,
                                                 long surfSpotLocationId );
    public SurfSpotLocation addSurfSpotLocation( String regionId,
                                     long surfSpotId,
                                     SurfSpotLocation surfSpotLocation );
    public SurfSpotLocation updateSurfSpotLocation( String regionId,
                                        long surfSpotId,
                                        long surfSpotLocationId,
                                        SurfSpotLocation surfSpotLocation );
    public SurfSpotLocation deleteSurfSpotLocation( String regionId,
                                        long surfSpotId,
                                        long surfSpotLocationId );

}
