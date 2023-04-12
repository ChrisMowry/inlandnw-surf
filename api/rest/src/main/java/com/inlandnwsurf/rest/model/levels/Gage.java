package com.inlandnwsurf.rest.model.levels;

import com.inlandnwsurf.rest.model.location.Location;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.net.URL;
import java.util.List;

@Data
@NoArgsConstructor
public class Gage {

    private String id;
    private String name;
    private List<GageValueRecord> values;
    private List<DailyGageRange> history;
    private Location location;
    private URL url;

}
