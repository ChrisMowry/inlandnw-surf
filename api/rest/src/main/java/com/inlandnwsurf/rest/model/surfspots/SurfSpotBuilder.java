package com.inlandnwsurf.rest.model.surfspots;

import com.inlandnwsurf.rest.model.levels.FlowSource;
import com.inlandnwsurf.rest.model.location.Location;
import com.inlandnwsurf.rest.model.location.Region;

import java.util.List;

public class SurfSpotBuilder {

    private SurfSpot surfSpot;

    public SurfSpotBuilder surfspot( SurfSpot surfspot ){
        surfSpot = surfspot;
        return this;
    }

    public SurfSpotBuilder id( long id ){
        surfSpot.setId(id);
        return this;
    }

    public SurfSpotBuilder region( Region region ){
        surfSpot.setRegion(region);
        return this;
    }

    public SurfSpotBuilder name( String name ){
        surfSpot.setName(name);
        return this;
    }

    public SurfSpotBuilder surfSpotLocations( List<SurfSpotLocation> surfSpotLocations){
        surfSpot.setSurfspots(surfSpotLocations);
        return this;
    }

    public SurfSpotBuilder location( Location location ){
        surfSpot.setLocation(location);
        return this;
    }

//    public void flowSource( List<FlowSource> flowSources){
//        List<SurfSpotLocations> surfSpotLocations =
//    }

    public SurfSpot build(){
        return surfSpot;
    }

}
