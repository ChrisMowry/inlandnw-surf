package com.inlandnwsurf.rest.model.levels;

import lombok.Data;

@Data
public class Unit {
    private String name;
    private String abbr;
    private FlowType flowType;
    private double conversionFactor;

}
