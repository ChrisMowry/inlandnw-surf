package com.inlandnwsurf.rest.controller;

import com.inlandnwsurf.rest.exception.ElementNotFoundException;
import com.inlandnwsurf.rest.model.levels.FlowSource;
import com.inlandnwsurf.rest.model.levels.Gage;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/api/flow-sources")
public interface FlowSourceOperations {

    @GetMapping("")
    ResponseEntity<List<FlowSource>> getFlowSources();
    @GetMapping("/{flowSourceId}")
    ResponseEntity<FlowSource> getFlowSource(
            @PathVariable long flowSourceId )
            throws ElementNotFoundException;
    @PostMapping("")
    ResponseEntity<FlowSource> createFlowSource(
            @RequestBody FlowSource flowSource );
    @PutMapping("/{flowSourceId}")
    ResponseEntity<FlowSource> updateFlowSource(
            @PathVariable long flowSourceId,
            @RequestBody FlowSource flowSource)
            throws ElementNotFoundException;;
    @DeleteMapping("/{flowSourceId}")
    ResponseEntity<FlowSource> deleteFLowSource(
            @PathVariable long flowSourceId )
            throws ElementNotFoundException;;
    @GetMapping("/{flowSourceId}/gages")
    ResponseEntity<List<Gage>> getGages(
            @PathVariable long flowSourceId )
            throws ElementNotFoundException;;
    @GetMapping("/{flowSourceId}/gages/{gageId}")
    ResponseEntity<Gage> getGage(
            @PathVariable long flowSourceId,
            @PathVariable String gageId )
            throws ElementNotFoundException;;
    @PostMapping("/{flowSourceId}/gages")
    ResponseEntity<Gage> createGage(
            @PathVariable long flowSourceId,
            @RequestBody Gage gage)
            throws ElementNotFoundException;;
    @PutMapping("/{flowSourceId}/gages/{gageId}")
    ResponseEntity<Gage> updateGage(
            @PathVariable long flowSourceId,
            @PathVariable String gageId,
            @RequestBody Gage gage)
            throws ElementNotFoundException;;
    @DeleteMapping("/{flowSourceId}/gages/{gageId}")
    ResponseEntity<Gage> deleteGage(
            @PathVariable long flowSourceId,
            @RequestBody String gageId )
            throws ElementNotFoundException;;
}
