package com.inlandnwsurf.rest.model.levels;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class DailyGageValue {
    private int day;
    private int month;
    private int year;
    private Flow minFlow;
    private Flow maxFlow;

}
