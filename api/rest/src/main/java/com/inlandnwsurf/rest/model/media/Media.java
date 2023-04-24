package com.inlandnwsurf.rest.model.media;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.net.URL;

@Data
@NoArgsConstructor
public class Media {

    private int order;
    private String name;
    private String url;

}
