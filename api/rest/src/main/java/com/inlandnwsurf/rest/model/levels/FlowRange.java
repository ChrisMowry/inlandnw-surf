package com.inlandnwsurf.rest.model.levels;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FlowRange {
    private Flow minFlow;
    private Flow maxFlow;
}
