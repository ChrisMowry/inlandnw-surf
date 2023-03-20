package com.inlandnwsurf.rest.model.management;

import lombok.Data;

import java.time.LocalDateTime;
@Data
public class ManagedItem {

    private User createdBy;
    private LocalDateTime createdOn;
    private User modifiedBy;
    private LocalDateTime modifiedOn;

    public ManagedItem(){
        this.createdBy = null;
        this.createdOn = null;
        this.modifiedBy = null;
        this.modifiedOn = null;
    }

    public ManagedItem(User user){
        this.createdBy = user;
        this.createdOn = LocalDateTime.now();
        this.modifiedBy = user;
        this.modifiedOn = LocalDateTime.now();
    }

    public void modified(User user){
        this.modifiedBy = user;
        this.modifiedOn = LocalDateTime.now();
    }
}
