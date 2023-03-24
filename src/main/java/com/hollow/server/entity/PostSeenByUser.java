package com.hollow.server.entity;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import lombok.Data;
import com.hollow.server.mapper.PostMapper;

@Data
public class PostSeenByUser extends Post {
    /*
     * In controller, it is the post returned which carries the collect-status and comment of THE user.
     */
    private int collect;
    private int comment;
    private int c1;
    private int c2;
    private int c3;
    private int c4;
    private int c5;
    private int c6;
    private int c7;
    private int c8;

    @Autowired
    PostMapper postMapper;

    public PostSeenByUser() {

    }

    public List<PostSeenByUser> postToList(List<Post> postList, long uid) {
        
        List<PostSeenByUser> postSeenByUserList = new ArrayList<>();
        
        for (Post post : postList) {
            
            PostSeenByUser postSeenByUser = new PostSeenByUser();

            postSeenByUser.setPid(pid = post.getPid());
            postSeenByUser.setUid(post.getUid());
            postSeenByUser.setWid(post.getWid());
            postSeenByUser.setMid(post.getMid());
            postSeenByUser.setText(post.getText());
            postSeenByUser.setSetTime(post.getSetTime());
            postSeenByUser.setCreateAt(post.getCreateAt());

            CommentCount commentCount = postMapper.getCommentCount(pid).get(0);
            postSeenByUser.c1 = commentCount.getC1();
            postSeenByUser.c2 = commentCount.getC2();
            postSeenByUser.c3 = commentCount.getC3();
            postSeenByUser.c4 = commentCount.getC4();
            postSeenByUser.c5 = commentCount.getC5();
            postSeenByUser.c6 = commentCount.getC6();
            postSeenByUser.c7 = commentCount.getC7();
            postSeenByUser.c8 = commentCount.getC8();

            UserComment userComment = postMapper.commentCheck(uid, pid).get(0);
            postSeenByUser.collect = userComment.getCollect();
            postSeenByUser.comment = userComment.getComment();

            postSeenByUserList.add(postSeenByUser);
        }
        
        return postSeenByUserList;
    }
    
    public List<PostSeenByUser> commentToList(List<UserComment> commentList, long uid) {

        List<PostSeenByUser> postSeenByUserList = new ArrayList<>();

        for (UserComment comment : commentList) {

            Post post = postMapper.getPost(comment.getPid()).get(0);
            
            PostSeenByUser postSeenByUser = new PostSeenByUser();

            postSeenByUser.setPid(pid = post.getPid());
            postSeenByUser.setUid(post.getUid());
            postSeenByUser.setWid(post.getWid());
            postSeenByUser.setMid(post.getMid());
            postSeenByUser.setText(post.getText());
            postSeenByUser.setSetTime(post.getSetTime());
            postSeenByUser.setCreateAt(post.getCreateAt());

            CommentCount commentCount = postMapper.getCommentCount(pid).get(0);
            postSeenByUser.c1 = commentCount.getC1();
            postSeenByUser.c2 = commentCount.getC2();
            postSeenByUser.c3 = commentCount.getC3();
            postSeenByUser.c4 = commentCount.getC4();
            postSeenByUser.c5 = commentCount.getC5();
            postSeenByUser.c6 = commentCount.getC6();
            postSeenByUser.c7 = commentCount.getC7();
            postSeenByUser.c8 = commentCount.getC8();

            UserComment userComment = postMapper.commentCheck(uid, pid).get(0);
            postSeenByUser.collect = userComment.getCollect();
            postSeenByUser.comment = userComment.getComment();

            postSeenByUserList.add(postSeenByUser);
        }

        return postSeenByUserList;
    }
    
}
