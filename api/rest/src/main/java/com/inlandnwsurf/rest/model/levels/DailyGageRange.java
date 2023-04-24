package com.inlandnwsurf.rest.model.levels;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
public class DailyGageRange {
    private String date;
    private GageValue minGageFlow;
    private GageValue maxGageFlow;


}
