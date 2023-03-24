package com.hollow.server.testservice.impl;

import com.hollow.server.entity.User;
import com.hollow.server.mapper.UserMapper;
import com.hollow.server.testservice.TestUserServer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Description: TestUserServer实现类
 * @Author: jiangyong
 * @CreateDate: 2023/3/22 14:45
 * @Version: 1.0
 */
@Service
public class TestUserServerImpl implements TestUserServer {


    //导入Mapper接口
    @Autowired
    private UserMapper userMapper;

    @Override
    public List<User> query(Integer uid) {
        List<User> query = userMapper.query(uid);
        return query;
    }

    @Override
    public boolean update(String login, Integer uid) {
        boolean update = userMapper.update(login, uid);
        return update;
    }

    @Override
    public boolean insert() {
        boolean insert = userMapper.insert();
        return insert;
    }

    @Override
    public boolean delete(Integer uid) {
        boolean delete = userMapper.delete(uid);
        return delete;
    }

    @Override
    public List<User> queryAll() {
        List<User> users = userMapper.queryAll();
        return users;
    }
}
