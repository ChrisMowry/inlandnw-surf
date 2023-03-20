package com.inlandnwsurf.rest.model.management;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
public class User {

    private long id;
    private String name;
    private String email;
    private String phone;
    private String role;
    private LocalDateTime lastAccessTime;
}
