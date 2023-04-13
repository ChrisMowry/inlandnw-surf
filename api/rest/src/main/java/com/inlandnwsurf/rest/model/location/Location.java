package com.inlandnwsurf.rest.model.location;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Location {
    private String state;
    private Coordinates coordinates;
}
