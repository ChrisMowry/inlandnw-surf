package com.inlandnwsurf.rest.model.management;

import lombok.Data;

import java.time.Instant;
@Data
public class ManagedItem {

    private String createdBy;
    private Instant createdOn;
    private String modifiedBy;
    private Instant modifiedOn;

    public ManagedItem(){
        this.createdBy = null;
        this.createdOn = null;
        this.modifiedBy = null;
        this.modifiedOn = null;
    }

    public ManagedItem(String user){
        this.createdBy = user;
        this.createdOn = Instant.now();
        this.modifiedBy = user;
        this.modifiedOn = Instant.now();
    }

    public void modified(String user){
        this.modifiedBy = user;
        this.modifiedOn = Instant.now();
    }
}
