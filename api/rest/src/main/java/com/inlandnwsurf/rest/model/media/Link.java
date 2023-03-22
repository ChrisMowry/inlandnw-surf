package com.inlandnwsurf.rest.model.media;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.net.URL;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Link {
    private int order;
    private String label;
    private URL url;
}
