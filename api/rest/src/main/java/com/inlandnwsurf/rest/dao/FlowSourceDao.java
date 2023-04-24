package com.inlandnwsurf.rest.dao;

import com.inlandnwsurf.rest.model.levels.FlowSource;
import com.inlandnwsurf.rest.model.levels.Gage;

import java.util.List;

public interface FlowSourceDao {

    public List<FlowSource> getFlowSources();
    public FlowSource getFlowSource( long flowSourceId );
    public FlowSource createFlowSource( FlowSource flowSource );
    public FlowSource updateFlowSource( long flowSourceId, FlowSource flowSource );
    public FlowSource deleteFlowSource( long flowSourceId );
    public List<Gage> getGages( long flowSourceId );
    public Gage getGage( long flowSourceId, String gageId );
    public Gage createGage( long flowSourceId, String GageId );
    public Gage updateGage( long flowSourceId, String gageId );
    public Gage deleteGage (long flowSourceId, String gageId );

}
