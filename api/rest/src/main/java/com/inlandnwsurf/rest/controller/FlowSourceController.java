package com.inlandnwsurf.rest.controller;

import com.inlandnwsurf.rest.dao.FlowSourceDao;
import com.inlandnwsurf.rest.exception.ElementNotFoundException;
import com.inlandnwsurf.rest.model.levels.FlowSource;
import com.inlandnwsurf.rest.model.levels.Gage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class FlowSourceController implements FlowSourceOperations{

    private FlowSourceDao flowSourceDao;

    @Autowired
    FlowSourceController(FlowSourceDao flowSourceDao){
        this.flowSourceDao = flowSourceDao;
    }

    /**
     * @return
     */
    @Override
    public ResponseEntity<List<FlowSource>> getFlowSources() {
        List<FlowSource> flowSources = flowSourceDao.getFlowSources();
        return ResponseEntity.ok( flowSources );
    }

    /**
     * @param flowSourceId
     * @return
     */
    @Override
    public ResponseEntity<FlowSource> getFlowSource(long flowSourceId)
            throws ElementNotFoundException {
        FlowSource flowSource = flowSourceDao.getFlowSource(flowSourceId);
        return ResponseEntity.ok( flowSource );
    }

    /**
     * @param flowSource
     * @return
     */
    @Override
    public ResponseEntity<FlowSource> createFlowSource(FlowSource flowSource) {
        return null;
    }

    /**
     * @param flowSourceId
     * @param flowSource
     * @return
     */
    @Override
    public ResponseEntity<FlowSource> updateFlowSource(long flowSourceId, FlowSource flowSource)
            throws ElementNotFoundException {
        return null;
    }

    /**
     * @param flowSourceId
     * @return
     */
    @Override
    public ResponseEntity<FlowSource> deleteFLowSource(long flowSourceId)
            throws ElementNotFoundException {
        return null;
    }

    /**
     * @param flowSourceId
     * @return
     */
    @Override
    public ResponseEntity<List<Gage>> getGages(long flowSourceId)
            throws ElementNotFoundException {
        List<Gage> gages = flowSourceDao.getGages( flowSourceId );
        return ResponseEntity.ok( gages );
    }

    /**
     * @param flowSourceId
     * @param gageId
     * @return
     */
    @Override
    public ResponseEntity<Gage> getGage(long flowSourceId, String gageId)
            throws ElementNotFoundException {
        Gage gage = flowSourceDao.getGage(flowSourceId, gageId);
        return ResponseEntity.ok(gage);
    }

    /**
     * @param flowSourceId
     * @param gage
     * @return
     */
    @Override
    public ResponseEntity<Gage> createGage(long flowSourceId, Gage gage)
            throws ElementNotFoundException {
        return null;
    }

    /**
     * @param flowSourceId
     * @param gageId
     * @param gage
     * @return
     */
    @Override
    public ResponseEntity<Gage> updateGage(long flowSourceId, String gageId, Gage gage)
            throws ElementNotFoundException {
        return null;
    }

    /**
     * @param flowSourceId
     * @param gageId
     * @return
     */
    @Override
    public ResponseEntity<Gage> deleteGage(long flowSourceId, String gageId)
            throws ElementNotFoundException {
        return null;
    }

}
