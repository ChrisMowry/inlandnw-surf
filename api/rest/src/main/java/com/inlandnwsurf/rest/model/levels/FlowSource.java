package com.inlandnwsurf.rest.model.levels;

import com.inlandnwsurf.rest.model.management.ManagedItem;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;

@Data
@NoArgsConstructor
public class FlowSource extends ManagedItem {
    private String id;
    private Flow flow;
    private ArrayList<Gage> gages;
    private String gageExpression;
}
