package com.inlandnwsurf.rest.model.levels;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class DailyGageRange {
    private int day;
    private int month;
    private int year;
    private GageValue minGageFlow;
    private GageValue maxGageFlow;

}
