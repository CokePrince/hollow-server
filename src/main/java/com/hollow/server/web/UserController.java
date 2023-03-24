package com.hollow.server.web;

import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
    public ResponseHelper<Long> signup(@RequestParam String email, @RequestParam String name, @RequestParam String password, @RequestParam int role) {
        if ((email == null) || (email.isEmpty()) || (!Pattern.matches("^(\\w+([-.][A-Za-z0-9]+)*){3,18}@\\w+([-.][A-Za-z0-9]+)*\\.\\w+([-.][A-Za-z0-9]+)*$", email))) {
            return ResponseHelper.error(ResponseCode.WRONG_EMAIL);
        } else if (userMapper.signupCheckEmail(email).get(0) != null) {
            return ResponseHelper.error(ResponseCode.EMAIL_USED_ALREADY);
        } else if ((name == null) || (name.isEmpty()) ) {
            return ResponseHelper.error(ResponseCode.WRONG_NAME);
        } else if (userMapper.signupCheckName(name).get(0) != null) {
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
    public ResponseHelper<Long> signin(@RequestParam String identity, @RequestParam String password) {
        try {
            if (userMapper.userSigninCheck(identity).get(0) == null) {
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
    public ResponseHelper<Long> logout(@RequestParam long id) {
        try {
            userMapper.userLogout(id);
            return ResponseHelper.error(ResponseCode.SUCCESS);
        } catch (Exception e) {
            return ResponseHelper.error(ResponseCode.DATABASE_ERROR);
        }
    }

    @PostMapping("/editpassword")
    public ResponseHelper<Long> editPassword(@RequestParam long id, @RequestParam String password, @RequestParam String password_new) {
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
    public ResponseHelper<Long> editEmail(@RequestParam long id, @RequestParam String password, @RequestParam String email) {
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
