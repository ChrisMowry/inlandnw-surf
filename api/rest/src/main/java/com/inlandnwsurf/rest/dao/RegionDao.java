package com.inlandnwsurf.rest.dao;

import com.inlandnwsurf.rest.exception.ElementNotFoundException;
import com.inlandnwsurf.rest.model.location.Region;

import java.util.List;

public interface RegionDao {

    public List<Region> getRegions();

    public Region getRegion( String regionId ) throws ElementNotFoundException;

    public Region createRegion( Region region );

    public Region updateRegion( Region region, String regionId ) throws ElementNotFoundException;

    public Region deleteRegion( String regionId ) throws ElementNotFoundException;

}
