package com.inlandnwsurf.rest.model.media;

import lombok.Data;

import java.net.URL;

@Data
public class Link {
    private String label;
    private URL url;
}
