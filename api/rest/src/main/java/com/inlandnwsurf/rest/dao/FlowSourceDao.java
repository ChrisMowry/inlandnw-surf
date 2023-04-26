package com.inlandnwsurf.rest.dao;

import com.inlandnwsurf.rest.exception.ElementNotFoundException;
import com.inlandnwsurf.rest.model.levels.FlowSource;
import com.inlandnwsurf.rest.model.levels.Gage;

import java.util.List;

public interface FlowSourceDao {

    public List<FlowSource> getFlowSources();
    public FlowSource getFlowSource( long flowSourceId ) throws ElementNotFoundException;
    public FlowSource createFlowSource( FlowSource flowSource );
    public FlowSource updateFlowSource( long flowSourceId, FlowSource flowSource ) throws ElementNotFoundException;
    public FlowSource deleteFlowSource( long flowSourceId ) throws ElementNotFoundException;
    public List<Gage> getGages( long flowSourceId ) throws ElementNotFoundException;
    public Gage getGage( long flowSourceId, String gageId ) throws ElementNotFoundException;
    public Gage createGage( long flowSourceId, Gage gage ) throws ElementNotFoundException;
    public Gage updateGage( long flowSourceId, String gageId, Gage gage) throws ElementNotFoundException;
    public Gage deleteGage (long flowSourceId, String gageId ) throws ElementNotFoundException;

}
