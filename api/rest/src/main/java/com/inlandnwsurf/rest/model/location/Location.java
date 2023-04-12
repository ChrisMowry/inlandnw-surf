package com.inlandnwsurf.rest.model.location;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Location {

    private Region region;
    private String state;
    private Coordinates coordinates;

}
