package com.hollow.server.entity;

import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.ArrayList;
import java.util.List;
import com.hollow.server.mapper.PostMapper;

@SuppressWarnings("unused")
public class PostSeenByGuest extends Post {
    /*
     * Like PostSeenByUser, but collect-status and comment-status are invisible.
     */    
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

    public PostSeenByGuest() {}

    public List<PostSeenByGuest> postToList(List<Post> postList) {
        
        List<PostSeenByGuest> postSeenByGuestList = new ArrayList<>();
        
        for (Post post : postList) {
            
            PostSeenByGuest postSeenByGuest = new PostSeenByGuest();

            postSeenByGuest.setPid(pid = post.getPid());
            postSeenByGuest.setUid(post.getUid());
            postSeenByGuest.setWid(post.getWid());
            postSeenByGuest.setMid(post.getMid());
            postSeenByGuest.setText(post.getText());
            postSeenByGuest.setSetTime(post.getSetTime());
            postSeenByGuest.setCreateAt(post.getCreateAt());

            CommentCount commentCount = postMapper.getCommentCount(pid).get(0);
            postSeenByGuest.c1 = commentCount.getC1();
            postSeenByGuest.c2 = commentCount.getC2();
            postSeenByGuest.c3 = commentCount.getC3();
            postSeenByGuest.c4 = commentCount.getC4();
            postSeenByGuest.c5 = commentCount.getC5();
            postSeenByGuest.c6 = commentCount.getC6();
            postSeenByGuest.c7 = commentCount.getC7();
            postSeenByGuest.c8 = commentCount.getC8();

            postSeenByGuestList.add(postSeenByGuest);
        }
        
        return postSeenByGuestList;
    }    
}
