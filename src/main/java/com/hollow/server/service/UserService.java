package com.hollow.server.service;

import java.sql.Date;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hollow.server.mapper.UserMapper;
import com.hollow.server.entity.User;

@Service
@Transactional
public class UserService {

    @Autowired
    UserMapper userMapper;
    
    public Long signup(String email, String name, String password, int role) throws Exception {

        User user = new User();
        DigestGenerator dg = new DigestGenerator(password);

        user.setEmail(email);
        user.setName(name);
        user.setSalt(dg.getSalt());
        user.setDigest(dg.getDigest());
        user.setRole(role);
        user.setCreatedAt(new Date(System.currentTimeMillis()));
        userMapper.userSignup(user);
        return user.getUid();
    }

    public Long signin(String identity, String password) throws Exception {

        User user = userMapper.userSigninCheck(identity).get(0);
        int salt = user.getSalt();
        String digest = user.getDigest();
        DigestVerifier dv = new DigestVerifier(password, digest, salt);
        if (dv.verify()) {
            return user.getUid();
        } else {
            throw new IllegalArgumentException();
        }
        
    }

    public Long editPassword(long uid, String password, String passwordNew) throws Exception {

        User user = userMapper.userEditCheckPassword(uid).get(0);
        int salt = user.getSalt();
        String digest = user.getDigest();
        DigestVerifier dv = new DigestVerifier(password, digest, salt);
        if (dv.verify()) {
            DigestGenerator dg = new DigestGenerator(passwordNew);
            user.setSalt(dg.getSalt());
            user.setDigest(dg.getDigest());
            userMapper.userEditPassword(user);
            return user.getUid();
        } else {
            throw new IllegalArgumentException();
        }
    }

    public Long editEmail(long uid, String password, String email) throws Exception {

        User user = userMapper.userEditCheckPassword(uid).get(0);
        int salt = user.getSalt();
        String digest = user.getDigest();
        DigestVerifier dv = new DigestVerifier(password, digest, salt);
        if (dv.verify()) {
            user.setEmail(email);
            userMapper.userEditEmail(user);
            return user.getUid();
        } else {
            throw new IllegalArgumentException();
        }
    }
    
}
