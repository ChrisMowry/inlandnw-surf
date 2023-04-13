package com.inlandnwsurf.rest.model.surfspots;

import com.inlandnwsurf.rest.model.location.Location;
import com.inlandnwsurf.rest.model.location.Region;
import com.inlandnwsurf.rest.model.management.ManagedItem;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class SurfSpot extends ManagedItem {

    private long id;
    private Region region;
    private String name;
    private List<SurfSpotLocation> surfspots;
    private Location location;

}