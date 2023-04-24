package com.inlandnwsurf.rest.controller;

import com.inlandnwsurf.rest.model.location.Region;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

// TODO: Add Swagger annotations
@RequestMapping("/api/regions")
public interface RegionOperations {

    @GetMapping("")
    public ResponseEntity<List<Region>> getRegions();

    @GetMapping("/{regionId}")
    public ResponseEntity<Region> getRegion(@PathVariable String regionId);

    @PostMapping("")
    public ResponseEntity<Region> createRegion(@RequestBody Region region);

    @PutMapping("/{regionId}")
    public ResponseEntity<Region> updateRegion(@PathVariable String regionId, @RequestBody Region region);

    @DeleteMapping("/{regionId}")
    public ResponseEntity<Region> deleteRegion(@PathVariable String regionId);
}
