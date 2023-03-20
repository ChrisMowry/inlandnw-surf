package com.inlandnwsurf.rest.model.levels;

import com.inlandnwsurf.rest.model.location.Location;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.ArrayList;

@Data
@NoArgsConstructor
public class Gage {

    private String id;
    private String name;
    private Flow currentFlow;
    private LocalDateTime latestValueDateTime;
    private ArrayList<DailyGageValue> history;
    private Location location;

}
