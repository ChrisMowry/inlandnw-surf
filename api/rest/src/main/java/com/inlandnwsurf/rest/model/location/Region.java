package com.inlandnwsurf.rest.model.location;

import java.util.ArrayList;

import com.inlandnwsurf.rest.model.management.ManagedItem;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Region extends ManagedItem {
    private long id;
    private String name;
    private ArrayList<Coordinates> boundary;
}
