package com.hollow.server.service;

import java.security.MessageDigest;
import java.math.BigInteger;

public class DigestGenerator {
    /*
     * A random number is attached to the SHA-256 form password receving from http request and ciphered altogether,
     * which hopes to prevent attackers from deducing password with a rainbow table.
     */

    private String password;
    private int salt = (int)(Math.random()*10000);
    private MessageDigest md;
    private String digest;

    public DigestGenerator(String password) {
        this.password = password;
    }

    public String getDigest() throws Exception {
        md = MessageDigest.getInstance("SHA-256");
        md.update((password+salt).getBytes("UTF-8"));
        digest = new BigInteger(1, md.digest()).toString(16);
        return digest;
    }

    public int getSalt() {
        return salt;
    }
    
}
