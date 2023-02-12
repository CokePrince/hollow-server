package com.hollow.server.service;

import java.util.List;
import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hollow.server.entity.CommentCount;
import com.hollow.server.mapper.PostMapper;

@Service
@Transactional
public class HeatService {

    @Autowired
    PostMapper postMapper;

    @Scheduled(initialDelay = 60_000, fixedRate = 1800_000)
    public void countHeat() {
        LocalDate now = LocalDate.now();
        String threeDaysAgo = now.minusDays(3).toString();
        String weekAgo = now.minusDays(7).toString();
        String monthAgo = now.minusMonths(1).toString();

        List<CommentCount> postThreeDaysAgo = postMapper.postThreeDaysAgo(threeDaysAgo);
        for (CommentCount commentCount : postThreeDaysAgo) {
            double heat = commentCount.getC1() + commentCount.getC2() + commentCount.getC3() + commentCount.getC4() + commentCount.getC5() + commentCount.getC6() + commentCount.getC7() + commentCount.getC8();
            long pid = commentCount.getPid();
            postMapper.setHeat(heat, pid);
        }

        List<CommentCount> postWeekAgo = postMapper.postWeekAgo(threeDaysAgo, weekAgo);
        for (CommentCount commentCount : postWeekAgo) {
            double heat = 0.7*(commentCount.getC1() + commentCount.getC2() + commentCount.getC3() + commentCount.getC4() + commentCount.getC5() + commentCount.getC6() + commentCount.getC7() + commentCount.getC8());
            long pid = commentCount.getPid();
            postMapper.setHeat(heat, pid);
        }

        List<CommentCount> postMonthAgo = postMapper.postMonthAgo(weekAgo, monthAgo);
        for (CommentCount commentCount : postMonthAgo) {
            double heat = 0.5*(commentCount.getC1() + commentCount.getC2() + commentCount.getC3() + commentCount.getC4() + commentCount.getC5() + commentCount.getC6() + commentCount.getC7() + commentCount.getC8());
            long pid = commentCount.getPid();
            postMapper.setHeat(heat, pid);
        }

        List<CommentCount> postMonthBefore = postMapper.postMonthBefore(monthAgo);
        for (CommentCount commentCount : postMonthBefore) {
            double heat = 0.4*(commentCount.getC1() + commentCount.getC2() + commentCount.getC3() + commentCount.getC4() + commentCount.getC5() + commentCount.getC6() + commentCount.getC7() + commentCount.getC8());
            long pid = commentCount.getPid();
            postMapper.setHeat(heat, pid);
        }

    }
    
}
