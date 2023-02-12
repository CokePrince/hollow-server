package com.hollow.server.entity;

import java.sql.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import lombok.Data;

@Entity
@Data
public class User {
    
    private long uid;

    @Column(unique = true)
    private String email;
    
    @Column(unique = true)
    private String name;

    private String digest;
    private int salt;
    private int role;
    private int login;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",locale = "zh",timezone = "GMT+8")
    private Date createdAt;

    public Boolean verifyAdmin(User user) {
        return user.role == 1;
    }

}
