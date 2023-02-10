package com.hollow.server.service;

import java.security.MessageDigest;
import java.util.Objects;
import java.math.BigInteger;

public class DigestVerifier {
    private String password;
    private String digest;
    private int salt;
    private MessageDigest md;
    
    public DigestVerifier(String password, String digest, int salt) {
        this.password = password;
        this.digest = digest;
        this.salt = salt;
    }

    public Boolean verify() throws Exception {
        md = MessageDigest.getInstance("SHA-256");
        md.update((password+salt).getBytes("UTF-8"));
        return Objects.equals(new BigInteger(1, md.digest()).toString(16), digest);
    }
}
