package com.inlandnwsurf.rest.model.location;

import com.inlandnwsurf.rest.model.management.ManagedItem;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class Region extends ManagedItem {
    private String id;
    private String name;
    private List<Coordinates> boundary;
}
