package com.example.coffee2.service.likePosts.impl;

import com.example.coffee2.entity.LikePostsEntity;
import com.example.coffee2.reponsitory.Customer.LikePostsCustomer;
import com.example.coffee2.reponsitory.LikePostsRepository;
import com.example.coffee2.request.LikePostsRequest;
import com.example.coffee2.response.LikePostsResponse;
import com.example.coffee2.service.likePosts.LikePostsService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Log4j2
public class LikePostsServiceImpl implements LikePostsService {

    @Autowired
    private LikePostsRepository repository;

    @Autowired
    private LikePostsCustomer likePostsCustomer;

    @Override
    public List<LikePostsResponse> getListLikePosts(LikePostsRequest request) {
        return likePostsCustomer.getListLikePosts(request);
    }

    @Override
    public Long getCountListLikePosts(LikePostsRequest request) {
        return likePostsCustomer.getCountListLikePosts(request);
    }

    @Override
    public boolean isLike(LikePostsRequest request) {
        try {
//            List<LikePostsEntity> postId = repository.findByPostId(request.getPostId());
//            List<LikePostsEntity> userId = repository.findByUserId(request.getUserId());
            List<LikePostsEntity> checkExist = repository.findAllByPostIdAndUserId(request.getPostId(), request.getUserId());
            LikePostsEntity obj1 = repository.findByPostIdAndUserId(request.getPostId(), request.getUserId());
//            if (checkExist.contains(obj1)) {
            if (obj1 != null) {
                obj1.setIsLike(request.getIsLike());
                repository.save(obj1);
                return true;
            }
            LikePostsEntity obj = new LikePostsEntity();
            obj.setUserId(request.getUserId());
            obj.setPostId(request.getPostId());
            obj.setIsLike(request.getIsLike());
            obj.setIsSave(request.getIsSave());
            repository.save(obj);
            return true;
        } catch (Exception e) {
            log.error("not success: " + e.getMessage());
            return false;
        }
    }
}
