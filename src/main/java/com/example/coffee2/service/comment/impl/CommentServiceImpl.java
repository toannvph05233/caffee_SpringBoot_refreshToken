package com.example.coffee2.service.comment.impl;

import com.example.coffee2.entity.CommentEntity;
import com.example.coffee2.reponsitory.CommentRepository;
import com.example.coffee2.request.CommentRequest;
import com.example.coffee2.service.comment.CommentService;
import lombok.extern.log4j.Log4j2;
import org.apache.commons.lang3.RandomUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
@Log4j2
public class CommentServiceImpl implements CommentService {
    @Autowired
    private CommentRepository commentRepository;

    @Override
    public boolean create(CommentRequest request) {
        Date now = new Date();
        try {
            CommentEntity obj = new CommentEntity();
            obj.setCommentId(RandomUtils.nextLong(100000000, 1000000000));
            obj.setUserId(request.getUserId());
            obj.setPostId(request.getPostId());
            obj.setCommentText(request.getCommentText());
            obj.setCreateAt(String.valueOf(now));
            obj.setUpdateAt(request.getUpdateAt());
            obj.setLikeComment(request.getLikeComment());
            commentRepository.save(obj);
            return true;
        } catch (Exception e) {
            log.error("error: " + e.getMessage());
            return false;
        }
    }


    @Override
    public boolean update(CommentRequest request) {
        return false;
    }
}
