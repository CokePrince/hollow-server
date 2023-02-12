package com.hollow.server.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

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
    
}
