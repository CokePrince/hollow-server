package com.hollow.server.entity;

import jakarta.persistence.Entity;
import lombok.Data;

@Entity
@Data
public class UserComment {
    /* 
     * Here storages user's comment of and whether user has collected each post by uid.
     */
    private long uid;
    private long pid;
    private int collect;
    private int comment;
}
