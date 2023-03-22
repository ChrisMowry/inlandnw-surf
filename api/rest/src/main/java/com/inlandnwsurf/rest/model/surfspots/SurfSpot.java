package com.inlandnwsurf.rest.model.surfspots;

import com.inlandnwsurf.rest.model.location.Coordinates;
import com.inlandnwsurf.rest.model.location.Location;
import com.inlandnwsurf.rest.model.location.Region;
import com.inlandnwsurf.rest.model.location.State;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;

@Data
@NoArgsConstructor
class SurfSpot{

    private long id;
    private String name;
    private ArrayList<SurfSpotLocation> surfspots;
    private Location location;

}