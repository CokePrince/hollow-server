package com.hollow.server.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.hollow.server.entity.UserComment;
import com.hollow.server.entity.CommentCount;
import com.hollow.server.entity.Post;

@Mapper
public interface PostMapper {

    @Options(useGeneratedKeys = true, keyProperty = "pid", keyColumn = "pid")
    @Insert("INSERT INTO Post (uid, wid, mid, setTime, text, createdAt) VALUES (#{post.uid}, #{post.wid}, #{post.mid}, #{post.setTime}, #{post.text}, #{post.createdAt})")
    Void publishPost(@Param("post") Post post);

    @Insert("INSERT INTO Comment (pid, c1, c2, c3, c4, c5, c6, c7, c8, createAt) VALUES (#{post.pid}, 0, 0, 0, 0, 0, 0, 0, 0, #{post.createAt})")
    void publishCommentSpace(@Param("post") Post post);

    @Select("SELECT * FROM Post WHERE pid = #{pid}")
    List<Post> getPost(@Param("pid") long pid);

    @Select("SELECT * FROM CommentCount WHERE pid = #{pid}")
    List<CommentCount> getCommentCount(@Param("pid") long pid);

    @Select("SELECT collect, comment FROM UserComment WHERE uid = #{uid} AND pid = #{pid}")
    List<UserComment> commentCheck(@Param("uid") long uid, @Param("pid") long pid);

    @Insert("INSERT INTO UserComment (uid, pid, comment) VALUES (#{userComment.uid}, #{userComment.pid}, #{userComment.comment})")
    Void userCommentOnWhenNull(@Param("userComment") UserComment userComment);

    @Update("UPDATE UserComment SET comment = #{userComment.cid} WHERE uid = #{userComment.uid} AND pid = #{userComment.pid}")
    Void userCommentOn(@Param("userComment") UserComment userComment);

    @Update("UPDATE UserComment SET comment = #{userComment.cid} WHERE uid = #{userComment.uid} AND pid = #{userComment.pid}")
    Void userCommentChange(@Param("userComment") UserComment userComment);

    @Update("UPDATE UserComment SET comment = 0, WHERE uid = #{uid} AND pid = #{pid}")
    Void userCommentOff(@Param("uid") long uid, @Param("pid") long pid);
    
    @Update("UPDATE CommentCount SET #{cid} = #{cid} + 1 WHERE pid = #{pid}")
    Void countCommentOn(@Param("cid") String cid, @Param("pid") long pid);

    @Update("UPDATE CommentCount SET #{cid} = #{cid} + 1, #{cid_old} = #{cid_old} - 1 WHERE pid = #{pid}")
    Void countCommentChange(@Param("cid") String cid, @Param("cid_old") String cid_old, @Param("pid") long pid);

    @Update("UPDATE CommentCount SET #{cid} = #{cid} - 1 WHERE pid = #{pid}")
    Void countCommentOff(@Param("cid") String cid, @Param("pid") long pid);

    @Insert("INSERT INTO UserComment (uid, pid, collect) VALUES (#{uid}, #{pid}, 1)")
    Void userCollectWhenNull(@Param("uid") long uid, @Param("pid") long pid);

    @Update("UPDATE UserComment SET collect = 1 WHERE uid = #{uid} AND pid = #{pid}")
    Void userCollect(@Param("uid") long uid, @Param("pid") long pid);

    @Update("UPDATE UserComment SET collect = 0, WHERE uid = #{uid} AND pid = #{pid}")
    Void userCollectOff(@Param("uid") long uid, @Param("pid") long pid);

    @Select("SELECT * FROM POST WHERE uid = #{uid} Limit 20 OFFSET #{offset}")
    List<Post> getHistoryPost(@Param("uid") long uid, @Param("offset") int offset);

    @Select("SELECT pid FROM UserComment WHERE uid = #{uid} AND collect = 1 Limit 20 OFFSET #{offset}")
    List<UserComment> getCollectionPost(@Param("uid") long uid, @Param("offset") int offset);

    @Select("SELECT * FROM POST WHERE uid = #{uid} AND text LIKE '%#{keyword}%'")
    List<Post> searchHistoryPost(@Param("uid") long uid, @Param("keyword") String keyword);

    @Select("SELECT * FROM CommentCount WHERE createAt >= #{threeDaysAgo}")
    List<CommentCount> postThreeDaysAgo(@Param("threeDaysAgo") String threeDaysAgo);
    
    @Select("SELECT * FROM CommentCount WHERE createAt < #{threeDaysAgo} AND <= #{weekAgo}")
    List<CommentCount> postWeekAgo(@Param("threeDaysAgo") String threeDaysAgo, @Param("weekAgo") String weekAgo);

    @Select("SELECT * FROM CommentCount WHERE createAt < #{weekAgo} AND <= #{monthAgo}")
    List<CommentCount> postMonthAgo(@Param("weekAgo") String weekAgo, @Param("monthAgo") String monthAgo);

    @Select("SELECT * FROM CommentCount WHERE createAt < #{monthAgo}")
    List<CommentCount> postMonthBefore(@Param("monthAgo") String monthAgo);

    @Update("UPDATE Post SET heat = #{heat} WHERE pid = #{pid}")
    void setHeat(@Param("heat") double heat, @Param("pid") long pid);

    @Select("SELECT * FROM Post ORDER BY heat DESC LIMIT 20 OFFSET #{offset}")
    List<Post> fetchPost(@Param("offset") int offset);
}
