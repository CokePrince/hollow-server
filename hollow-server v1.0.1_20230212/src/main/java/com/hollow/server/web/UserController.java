package com.hollow.server.web;

import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.hollow.server.mapper.UserMapper;
import com.hollow.server.service.*;
import com.hollow.server.utils.*;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserService userService;

    @Autowired
    UserMapper userMapper;
    
    @PostMapping("/signup")
    public ResponseHelper<Long> signup(@RequestBody String email, @RequestBody String name, @RequestBody String password, @RequestBody int role) {
        if ((email == null) || (email.isEmpty()) || (!Pattern.matches("^(\\w+([-.][A-Za-z0-9]+)*){3,18}@\\w+([-.][A-Za-z0-9]+)*\\.\\w+([-.][A-Za-z0-9]+)*$", email))) {
            return ResponseHelper.error(ResponseCode.WRONG_EMAIL);
        } else if (userMapper.signupCheckEmail(email) != null) {
            return ResponseHelper.error(ResponseCode.EMAIL_USED_ALREADY);
        } else if ((name == null) || (name.isEmpty()) || (Pattern.matches("^(\\w+([-.][A-Za-z0-9]+)*){3,18}@\\w+([-.][A-Za-z0-9]+)*\\.\\w+([-.][A-Za-z0-9]+)*$", name))) {
            return ResponseHelper.error(ResponseCode.WRONG_NAME);
        } else if (userMapper.signupCheckName(name) != null) {
            return ResponseHelper.error(ResponseCode.NAME_USED_ALREADY);
        } else if ((password == null) || (password.isEmpty())) {
            return ResponseHelper.error(ResponseCode.EMPTY_PASSWORD);
        }
        try {
            if ("null".equals(String.valueOf(role))) {
                role = 0;
            }
            return new ResponseHelper<>(userService.signup(email, name, password, role));
        } catch (Exception e) {
            return ResponseHelper.error(ResponseCode.DATABASE_ERROR);
        }
    }

    @PostMapping("/signin")
    public ResponseHelper<Long> signin(@RequestBody String identity, @RequestBody String password) {
        try {
            if (userMapper.userSigninCheck(identity) == null) {
                return ResponseHelper.error(ResponseCode.USER_NOT_EXIST);
            } else if (userMapper.userSigninCheck(identity).get(0).getLogin() == 1) {
                return ResponseHelper.error(ResponseCode.LOGGED_ON_ALREADY);
            } else {
                return new ResponseHelper<>(userService.signin(identity, password));
            }
        } catch (Exception e) {
            return ResponseHelper.error(ResponseCode.WRONG_PASSWORD);
        }
    }

    @GetMapping("/logout")
    public ResponseHelper<Long> logout(@RequestBody long id) {
        try {
            userMapper.userLogout(id);
            return ResponseHelper.error(ResponseCode.SUCCESS);
        } catch (Exception e) {
            return ResponseHelper.error(ResponseCode.DATABASE_ERROR);
        }
    }

    @PostMapping("/editpassword")
    public ResponseHelper<Long> editPassword(@RequestBody long id, @RequestBody String password, @RequestBody String password_new) {
        if ((password_new == null) || (password_new.isEmpty())) {
            return ResponseHelper.error(ResponseCode.EMPTY_PASSWORD);
        }
        try {
            return new ResponseHelper<>(userService.editPassword(id, password, password_new));
        } catch (Exception e) {
            return ResponseHelper.error(ResponseCode.WRONG_PASSWORD);
        }
    }

    @PostMapping("/editemail")
    public ResponseHelper<Long> editEmail(@RequestBody long id, @RequestBody String password, @RequestBody String email) {
        if ((email == null) || (email.isEmpty()) || (!Pattern.matches("^(\\w+([-.][A-Za-z0-9]+)*){3,18}@\\w+([-.][A-Za-z0-9]+)*\\.\\w+([-.][A-Za-z0-9]+)*$", email))) {
            return ResponseHelper.error(ResponseCode.WRONG_EMAIL);
        }
        try {
            return new ResponseHelper<>(userService.editEmail(id, password, email));
        } catch (Exception e) {
            return ResponseHelper.error(ResponseCode.WRONG_PASSWORD);
        }
    }
}
