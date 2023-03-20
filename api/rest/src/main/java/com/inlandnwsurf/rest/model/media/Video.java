package com.inlandnwsurf.rest.model.media;

import com.inlandnwsurf.rest.model.management.ManagedItem;
import lombok.Data;

import java.net.URL;

@Data
public class Video {

    private long id;
    private String name;
    private URL url;
    private int order;

}
