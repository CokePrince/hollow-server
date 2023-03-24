package com.hollow.server.testservice;

import com.hollow.server.entity.User;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * @Description: 测试UserServer接口
 * @Author: jiangyong
 * @CreateDate: 2023/3/22 14:43
 * @Version: 1.0
 */
public interface TestUserServer {
    // 根据id查询
    List<User> query(Integer uid);

    // 根据id修改login参数
    boolean update(String login,Integer uid);

    // 添加数据
    boolean insert();

    // 根据id删除数据
    boolean delete(Integer uid);

    // 查询所有
    List<User> queryAll();
}
