package com.inlandnwsurf.rest.model.media;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.net.URL;

@Data
@NoArgsConstructor
public class Image {
    private long id;
    private String name;
    private URL url;
    private int order;
}
