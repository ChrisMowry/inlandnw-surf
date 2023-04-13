package com.inlandnwsurf.rest.model.levels;

import com.inlandnwsurf.rest.model.management.ManagedItem;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class FlowSource extends ManagedItem {
    private long id;
    private Flow flow;
    private List<Gage> gages;
    private String gageExpression;
}
