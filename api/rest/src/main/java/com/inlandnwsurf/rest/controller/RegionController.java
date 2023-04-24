package com.inlandnwsurf.rest.controller;

import com.inlandnwsurf.rest.dao.RegionDao;
import com.inlandnwsurf.rest.dao.SurfSpotDao;
import com.inlandnwsurf.rest.model.location.Region;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class RegionController implements RegionOperations{

    private RegionDao regionDao;

    @Autowired
    public RegionController(RegionDao regionDao){
        this.regionDao = regionDao;
    }


    /**
     * @return
     */
    @Override
    public ResponseEntity<List<Region>> getRegions() {

        return ResponseEntity.ok(regionDao.getRegions());
    }

    /**
     * @param regionId
     * @return
     */
    @Override
    public ResponseEntity<Region> getRegion(String regionId) {
        Region region = regionDao.getRegion(regionId);
        return ResponseEntity.ok(region);
    }

    /**
     * @param region
     * @return
     */
    @Override
    public ResponseEntity<Region> createRegion(Region region) {
        return null;
    }

    /**
     * @param regionId
     * @param region
     * @return
     */
    @Override
    public ResponseEntity<Region> updateRegion(String regionId, Region region) {
        return null;
    }

    /**
     * @param regionId
     * @return
     */
    @Override
    public ResponseEntity<Region> deleteRegion(String regionId) {
        return null;
    }
}
