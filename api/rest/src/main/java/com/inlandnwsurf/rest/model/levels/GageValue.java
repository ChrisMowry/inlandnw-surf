package com.inlandnwsurf.rest.model.levels;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GageValue {

    private double gageHeightValue;
    private Unit gageHeightUnit;
    private double dischargeValue;
    private Unit dischargeUnit;

}
