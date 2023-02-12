package com.hollow.server.service;

import java.sql.Date;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hollow.server.entity.Post;
import com.hollow.server.entity.PostSeenByUser;
import com.hollow.server.entity.UserComment;
import com.hollow.server.mapper.PostMapper;

@Service
@Transactional
public class PostService {

    @Autowired 
    PostMapper postMapper;

    public Long publish(long uid, int wid, int mid, long setTime, String text) throws Exception {
        
        Post post = new Post();
        Date date = new Date(System.currentTimeMillis());

        post.setUid(uid);
        post.setWid(wid);
        post.setMid(mid);
        post.setSetTime(new Date(setTime));
        post.setText(text);
        post.setCreateAt(date);
        postMapper.publishPost(post);
        long pid = post.getPid();
        postMapper.publishCommentSpace(post);
        return pid;
    }

    public PostSeenByUser comment(long uid, long pid, int cid) throws Exception {
        
        UserComment userComment = new UserComment();
        userComment.setUid(uid);
        userComment.setPid(pid);
        userComment.setComment(cid);
        
        int comment = postMapper.commentCheck(uid, pid).get(0).getComment();
        int collect = postMapper.commentCheck(uid, pid).get(0).getCollect();

        if ("null".equals(String.valueOf(comment))) {
            if ("null".equals(String.valueOf(collect))) {
                postMapper.userCommentOnWhenNull(userComment);
            } else {
                postMapper.userCommentOn(userComment);
            }
            postMapper.countCommentOn("c"+cid, pid);
        } else if (comment != cid) {
            postMapper.userCommentChange(userComment);
            postMapper.countCommentChange("c"+cid, "c"+comment, pid);
        } else if (comment == cid) {
            postMapper.userCommentOff(uid, pid);
            postMapper.countCommentOff("c"+cid, pid);
        }
        return new PostSeenByUser().postToList(postMapper.getPost(pid), uid).get(0);
    }

    public PostSeenByUser collect(long uid, long pid) throws Exception {

        int comment = postMapper.commentCheck(uid, pid).get(0).getComment();
        int collect = postMapper.commentCheck(uid, pid).get(0).getCollect();

        if ("null".equals(String.valueOf(collect))) {
            if ("null".equals(String.valueOf(comment))) {
                postMapper.userCollectWhenNull(uid, pid);
            } else {
                postMapper.userCollect(uid, pid);
            }
        } else if (collect == 0) {
            postMapper.userCollect(uid, pid);
        } else if (collect == 1) {
            postMapper.userCollectOff(uid, pid);
        }
        return new PostSeenByUser().postToList(postMapper.getPost(pid), uid).get(0);       
    }
        
}
