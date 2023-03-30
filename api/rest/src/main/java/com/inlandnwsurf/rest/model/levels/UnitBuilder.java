package com.inlandnwsurf.rest.model.levels;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class UnitBuilder {

    private Unit unit;
    private final double CONV_FACTOR_FT = 0.304800609601;
    private final double CONV_FACTOR_M = 1.000000000000;
    private final double CONV_FACTOR_CUBIC_FT = 0.028316847000;
    private final double CONV_FACTOR_CUBIC_M = 1.000000000000;

    public void meters(){
        this.unit.setName("meters");
        this.unit.setAbbr("m");
        this.unit.setConversionFactor(this.CONV_FACTOR_M);
    }

    public void feet(){
        this.unit.setName("feet");
        this.unit.setAbbr("ft");
        this.unit.setConversionFactor(this.CONV_FACTOR_FT);
    }

    public void cms(){
        this.unit.setName("cubic meters per second");
        this.unit.setAbbr("cms");
        this.unit.setConversionFactor(this.CONV_FACTOR_CUBIC_M);
    }

    public void cfs(){
        this.unit.setName("cubic feet per second");
        this.unit.setAbbr("cfs");
        this.unit.setConversionFactor(this.CONV_FACTOR_CUBIC_FT);
    }

    public Unit build(){
        return unit;
    }

}
