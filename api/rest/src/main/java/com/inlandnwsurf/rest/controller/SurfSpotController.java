package com.inlandnwsurf.rest.controller;

import com.inlandnwsurf.rest.dao.SurfSpotDao;
import com.inlandnwsurf.rest.exception.ElementNotFoundException;
import com.inlandnwsurf.rest.model.surfspots.SurfSpot;
import com.inlandnwsurf.rest.model.surfspots.SurfSpotLocation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class SurfSpotController implements SurfSpotOperations{
    private SurfSpotDao surfSpotDao;
    @Autowired
    public SurfSpotController(SurfSpotDao surfSpotDao){
        this.surfSpotDao = surfSpotDao;
    }


    /**
     * @param regionId
     * @return
     */
    @Override
    public ResponseEntity<List<SurfSpot>> getSurfSpots( String regionId )
            throws ElementNotFoundException{
        List<SurfSpot> surfspots = surfSpotDao.getSurfSpots( regionId );
        return ResponseEntity.ok( surfspots );
    }

    /**
     * @param regionId
     * @param surfspotId
     * @return
     */
    @Override
    public ResponseEntity<SurfSpot> getSurfSpot(String regionId, long surfspotId)
            throws ElementNotFoundException{
        SurfSpot surfSpot = surfSpotDao.getSurfSpot( regionId, surfspotId );
        return ResponseEntity.ok( surfSpot );
    }

    /**
     * @param regionId
     * @param surfspotId
     * @return
     */
    @Override
    public ResponseEntity<List<SurfSpotLocation>> getSurfSpotLocations(String regionId, long surfspotId)
            throws ElementNotFoundException {
        List<SurfSpotLocation> surfSpotLocations = surfSpotDao.getSurfSpotLocations(
                regionId,
                surfspotId );
        return ResponseEntity.ok( surfSpotLocations );
    }

    /**
     * @param regionId
     * @param surfspotId
     * @param surfspotLocId
     * @return
     */
    @Override
    public ResponseEntity<SurfSpotLocation> getSurfSpotLocation(String regionId,
                                                                long surfspotId,
                                                                long surfspotLocId)
            throws ElementNotFoundException{
        SurfSpotLocation surfSpotLocation = surfSpotDao.getSurfSpotLocation(
                regionId,
                surfspotId,
                surfspotLocId);
        return ResponseEntity.ok( surfSpotLocation );
    }
}
