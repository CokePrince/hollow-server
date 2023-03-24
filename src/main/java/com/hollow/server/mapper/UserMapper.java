package com.hollow.server.mapper;

import java.util.List;

import org.apache.ibatis.annotations.*;

import com.hollow.server.entity.User;

@Mapper
public interface UserMapper {
    @Select("SELECT uid FROM User WHERE email = #{email}")
    List<User> signupCheckEmail(@Param("email") String email);

    @Select("SELECT uid FROM User WHERE name = #{user.name}")
    List<User> signupCheckName(@Param("name") String name);

    @Options(useGeneratedKeys = true, keyProperty = "uid", keyColumn = "uid")
    @Insert("INSERT INTO User (email, name, digest, salt, role, createdAt) VALUES (#{user.email}, #{user.name}, #{user.digest}, #{user.salt}, #{user.role}, #{user.createdAt})")
    void userSignup(@Param("user") User user);

    @Select("SELECT login, salt, digest FROM User WHERE email = #{identity} OR name = #{identity}")
    List<User> userSigninCheck(@Param("identity") String identity);

    @Update("UPDATE User SET login = 1 WHERE email = #{identity} OR name = #{identity}")
    void userSignin(@Param("identity") String identity);

    @Update("UPDATE User SET login = 1 WHERE uid = #{uid}")
    void userLogout(@Param("uid") long uid);

    @Select("SELECT salt, digest FROM User WHERE uid = #{uid}")
    List<User> userEditCheckPassword(@Param("uid") long uid);

    @Update("UPDATE User SET salt = #{user.salt}, digest = #{user.digest} WHERE uid = #{user.uid}")
    void userEditPassword(@Param("user") User user);

    @Update("UPDATE User SET email = #{user.email} WHERE uid = #{user.uid}")
    void userEditEmail(@Param("user") User user);

    /**
    * @Description:    下列四个接口是测试接口
    * @Author:         jiangyong
    * @CreateDate:     2023/3/22 14:33
    * @UpdateUser:     jiangyong
    * @UpdateDate:     2023/3/22 14:33
    * @UpdateRemark:   修改内容
    * @Version:        1.0
    */

    // 查询所有
    @Select("SELECT * FROM User")
    List<User> queryAll();


    // 根据id查询
    @Select("SELECT * FROM User WHERE uid = #{uid}")
    List<User> query(@Param("uid") Integer uid);

    // 根据id修改login参数
    @Update("UPDATE User SET login = #{login} WHERE uid =#{uid} ")
    boolean update(@Param("login") String login,@Param("uid")Integer uid);

    // 添加数据
    @Insert("INSERT INTO `hollow_server`.`user` (`uid`, `email`, `name`, `digest`," +
            " `salt`, `role`, `createdAt`, `login`) " +
            "VALUES (null , '1355002656@qq.com', '测试2', '30', '1', '1', '2023-03-22 15:12:23', '1');\n ")
    boolean insert();

    // 根据id删除数据
    @Delete("delete from SET user  WHERE uid =#{uid} ")
    boolean delete(@Param("uid")Integer uid);
}
