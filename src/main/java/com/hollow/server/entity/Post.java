package com.hollow.server.entity;

import java.sql.Date;

import org.hibernate.annotations.Proxy;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.Entity;
import lombok.Data;

@Entity
@Proxy(lazy = false)
@Data
public class Post {
    protected long pid;
    private long uid;
    private int wid;
    private int mid;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",locale = "zh",timezone = "GMT+8")
    private Date setTime;  
    private String text;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",locale = "zh",timezone = "GMT+8")
    private Date createAt;
    private double heat;
}
