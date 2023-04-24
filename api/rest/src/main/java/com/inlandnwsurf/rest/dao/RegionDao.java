package com.inlandnwsurf.rest.dao;

import com.inlandnwsurf.rest.model.location.Region;

import java.util.List;

public interface RegionDao {

    public List<Region> getRegions();

    public Region getRegion( String regionId );

    public Region createRegion( Region region );

    public Region updateRegion( Region region, String regionId );

    public Region deleteRegion( String regionId );

}
