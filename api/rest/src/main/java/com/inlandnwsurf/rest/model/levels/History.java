package com.inlandnwsurf.rest.model.levels;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class History {
    private ArrayList<HistoryValue> values;
}