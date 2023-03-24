package com.hollow.server.web;

import com.hollow.server.entity.User;
import com.hollow.server.testservice.TestUserServer;
import com.hollow.server.utils.ResponseCode;
import com.hollow.server.utils.ResponseHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * @Description: 测试单表增删查改
 * @Author: jiangyong
 * @CreateDate: 2023/3/22 14:29
 * @Version: 1.0
 */
@RestController
@RequestMapping("/test")
public class TestControll {

//    导入service接口
    @Autowired
    private TestUserServer testUserServer;


    /**
     * @Description:    查询所有
     * @Author:         jiangyong
     * @CreateDate:     2023/3/22 14:31
     * @UpdateUser:     jiangyong
     * @UpdateDate:     2023/3/22 14:31
     * @UpdateRemark:   修改内容
     * @Version:        1.0
     */
    @PostMapping("/queryAll")
    public ResponseHelper<Long> queryAll() {
        List<User> query =new ArrayList<>();
        try {
            query = testUserServer.queryAll();
        }catch (Exception e){
            e.printStackTrace();
        }
        return new ResponseHelper(query);
    }


    /**
    * @Description:    根据ID查询
    * @Author:         jiangyong
    * @CreateDate:     2023/3/22 14:31
    * @UpdateUser:     jiangyong
    * @UpdateDate:     2023/3/22 14:31
    * @UpdateRemark:   修改内容
    * @Version:        1.0
    */
    @PostMapping("/query")
    public ResponseHelper<Long> query(@RequestParam("uid") Integer uid) {
        List<User> query =new ArrayList<>();
        try {
            query = testUserServer.query(uid);
        }catch (Exception e){
            e.printStackTrace();
        }
        return new ResponseHelper(query);
    }

    /**
     * @Description:    根据ID修改
     * @Author:         jiangyong
     * @CreateDate:     2023/3/22 14:31
     * @UpdateUser:     jiangyong
     * @UpdateDate:     2023/3/22 14:31
     * @UpdateRemark:   修改内容
     * @Version:        1.0
     */
    @PostMapping("/update")
    public ResponseHelper<Long> update(@RequestParam("login") String login,@RequestParam("uid") Integer uid) {
         boolean update=false;
        try {
              update = testUserServer.update(login, uid);
        }catch (Exception e){
            e.printStackTrace();
        }
        return new ResponseHelper(update);
    }

    /**
     * @Description:    添加数据方法
     * @Author:         jiangyong
     * @CreateDate:     2023/3/22 14:31
     * @UpdateUser:     jiangyong
     * @UpdateDate:     2023/3/22 14:31
     * @UpdateRemark:   修改内容
     * @Version:        1.0
     */
    @PostMapping("/insert")
    public ResponseHelper<Long> insert() {
        boolean insert=false;
        try {
            insert = testUserServer.insert();
        }catch (Exception e){
            e.printStackTrace();
        }
        return new ResponseHelper(insert);
    }
    /**
     * @Description:    根据ID删除
     * @Author:         jiangyong
     * @CreateDate:     2023/3/22 14:31
     * @UpdateUser:     jiangyong
     * @UpdateDate:     2023/3/22 14:31
     * @UpdateRemark:   修改内容
     * @Version:        1.0
     */
    @PostMapping("/delete")
    public ResponseHelper<Long> delete(@RequestParam("uid") Integer uid) {
        boolean delete=false;
        try {
            delete = testUserServer.delete(uid);
        }catch (Exception e){
            e.printStackTrace();
        }
        return new ResponseHelper(delete);
    }

}
