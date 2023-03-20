package com.inlandnwsurf.rest.model.media;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Section {

    private String heading;
    private String paragraph;
    private int order;

}
