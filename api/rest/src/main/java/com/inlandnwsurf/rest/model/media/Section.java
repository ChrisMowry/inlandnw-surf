package com.inlandnwsurf.rest.model.media;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Section {

    private int order;
    private String heading;
    private String paragraph;

}
