package com.hollow.server.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.hollow.server.entity.PostSeenByUser;
import com.hollow.server.entity.PostSeenByGuest;
import com.hollow.server.service.PostService;
import com.hollow.server.mapper.PostMapper;
import com.hollow.server.utils.ResponseCode;
import com.hollow.server.utils.ResponseHelper;


@RestController
@RequestMapping("/post")
public class PostController {

    @Autowired
    PostService postService;

    @Autowired
    PostMapper postMapper;

    @PostMapping("/publish")
    public ResponseHelper<Long> publish(@RequestParam long id, @RequestParam String text, @RequestParam int w_id, @RequestParam int m_id, @RequestParam long time) {
        if (text == null) {
            return ResponseHelper.error(ResponseCode.WRONG_REQUEST);
        }
        try {
            return new ResponseHelper<>(postService.publish(id, w_id, m_id, time, text));
        } catch (Exception e) {
            return ResponseHelper.error(ResponseCode.DATABASE_ERROR);
        }        
    }

    @PostMapping("/comment")
    public ResponseHelper<PostSeenByUser> comment(@RequestParam long p_id, @RequestParam long id, @RequestParam int c_id) {
        try {
            return new ResponseHelper<>(postService.comment(id, p_id, c_id));
        } catch (Exception e) {
            return ResponseHelper.error(ResponseCode.DATABASE_ERROR);
        }
    }

    @PostMapping("/collect")
    public ResponseHelper<PostSeenByUser> comment(@RequestParam long p_id, @RequestParam long id) {
        try {
            return new ResponseHelper<>(postService.collect(id, p_id));
        } catch (Exception e) {
            return ResponseHelper.error(ResponseCode.DATABASE_ERROR);
        }
    }

    @PostMapping("/history")
    public ResponseHelper<List<PostSeenByUser>> historyPost(@RequestParam long id, @RequestParam int offset) {
        try {
            return new ResponseHelper<>(new PostSeenByUser().postToList(postMapper.getHistoryPost(id, offset), id));
        } catch (Exception e) {
            return ResponseHelper.error(ResponseCode.DATABASE_ERROR);
        }
    }

    @PostMapping("/collection")
    public ResponseHelper<List<PostSeenByUser>> collectionPost(@RequestParam long id, @RequestParam int offset) {
        try {
            return new ResponseHelper<>(new PostSeenByUser().commentToList(postMapper.getCollectionPost(id,offset), id));
        } catch (Exception e) {
            return ResponseHelper.error(ResponseCode.DATABASE_ERROR);
        }
    }
    
    @PostMapping("/serch_mine")
    public ResponseHelper<List<PostSeenByUser>> searchHistory(@RequestParam long id, @RequestParam String keyword) {
        try {
            return new ResponseHelper<>(new PostSeenByUser().postToList(postMapper.searchHistoryPost(id, keyword), id));
        } catch (Exception e) {
            return ResponseHelper.error(ResponseCode.DATABASE_ERROR);
        }
    }

    @PostMapping("/fetch")
    public ResponseHelper<List<PostSeenByUser>> fetch(@RequestParam long id, @RequestParam int offset) {
        try {
            return new ResponseHelper<>(new PostSeenByUser().postToList(postMapper.fetchPost(offset), id));
        } catch (Exception e) {
            return ResponseHelper.error(ResponseCode.DATABASE_ERROR);
        }
    }

    @PostMapping("/fetchout")
    public ResponseHelper<List<PostSeenByGuest>> fetchOut(@RequestParam long id, @RequestParam int offset) {
        try {
            return new ResponseHelper<>(new PostSeenByGuest().postToList(postMapper.fetchPost(offset)));
        } catch (Exception e) {
            return ResponseHelper.error(ResponseCode.DATABASE_ERROR);
        }
    }
}
