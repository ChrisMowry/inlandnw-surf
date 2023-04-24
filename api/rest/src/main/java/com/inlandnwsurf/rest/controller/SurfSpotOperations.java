package com.inlandnwsurf.rest.controller;

import com.inlandnwsurf.rest.model.surfspots.SurfSpot;
import com.inlandnwsurf.rest.model.surfspots.SurfSpotLocation;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

// TODO: Add Swagger annotations
@RequestMapping("/api/surfspots")
public interface SurfSpotOperations {

    @GetMapping("/{regionId}")
    public ResponseEntity<List<SurfSpot>> getSurfSpots(
            @PathVariable String regionId );

    @GetMapping("/{regionId}/{surfspotId}")
    public ResponseEntity<SurfSpot> getSurfSpot(
            @PathVariable String regionId,
            @PathVariable long surfspotId );

    @GetMapping("/{regionId}/{surfspotId}/spots")
    public ResponseEntity<List<SurfSpotLocation>> getSurfSpotLocations(
            @PathVariable String regionId,
            @PathVariable long surfspotId );

    @GetMapping("/{regionId}/{surfspotId}/spots/{surfspotLocId}")
    public ResponseEntity<SurfSpotLocation> getSurfSpotLocation(
            @PathVariable String regionId,
            @PathVariable long surfspotId,
            @PathVariable long surfspotLocId);

}
