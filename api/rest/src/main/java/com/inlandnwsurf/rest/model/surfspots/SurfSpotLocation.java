package com.inlandnwsurf.rest.model.surfspots;

import com.inlandnwsurf.rest.model.levels.*;
import com.inlandnwsurf.rest.model.location.Location;
import com.inlandnwsurf.rest.model.management.ManagedItem;
import com.inlandnwsurf.rest.model.media.Image;
import com.inlandnwsurf.rest.model.media.Link;
import com.inlandnwsurf.rest.model.media.Section;
import com.inlandnwsurf.rest.model.media.Video;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
@Data
@NoArgsConstructor
public class SurfSpotLocation extends ManagedItem {
    private long id;
    private String name;
    private ArrayList<Section> description;
    private Difficulty difficulty;
    private String tricks;
    private String features;
    private Location location;
    private Location access;
    private FlowSource currentFlow;
    private FlowRange flowRange;
    private FlowRange optimumFlowRange;
    private ArrayList<FlowInfo> flowInfo;
    private ArrayList<Link> links;
    private ArrayList<Image> images;
    private ArrayList<Video> videos;
    private History history;
}