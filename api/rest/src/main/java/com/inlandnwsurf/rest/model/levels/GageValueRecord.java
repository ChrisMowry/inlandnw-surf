package com.inlandnwsurf.rest.model.levels;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GageValueRecord {
    private GageValue value;
    private Instant timestamp;
}
