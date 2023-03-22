package com.inlandnwsurf.rest.model.location;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Coordinates {
    private String espg;
    private float lat;
    private float lng;
}
