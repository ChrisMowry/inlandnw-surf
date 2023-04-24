package com.inlandnwsurf.rest.model.surfspots;

import com.inlandnwsurf.rest.model.levels.*;
import com.inlandnwsurf.rest.model.location.Location;
import com.inlandnwsurf.rest.model.management.ManagedItem;
import com.inlandnwsurf.rest.model.media.Link;
import com.inlandnwsurf.rest.model.media.Media;
import com.inlandnwsurf.rest.model.media.Section;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class SurfSpotLocation extends ManagedItem {
    private long id;
    private String name;
    private List<Section> description;
    private Difficulty difficulty;
    private String tricks;
    private String features;
    private Location location;
    private Location access;
    private FlowSource currentFlow;
    private FlowRange flowRange;
    private FlowRange optimumFlowRange;
    private List<FlowInfo> flowInfo;
    private List<Link> links;
    private List<Media> images;
    private List<Media> videos;
    private List<HistoryValue> history;

}