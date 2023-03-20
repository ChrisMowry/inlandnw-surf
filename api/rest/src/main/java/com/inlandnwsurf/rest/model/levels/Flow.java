package com.inlandnwsurf.rest.model.levels;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Flow {
    private Unit gageHeight;
    private Unit discharge;

}
