package com.hollow.server.entity;

import java.sql.Date;

import org.hibernate.annotations.Proxy;

import com.fasterxml.jackson.annotation.JsonFormat;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Proxy(lazy = false)
@Data
@Table(name="user")
public class User {

    @Id
    private Long uid;

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
