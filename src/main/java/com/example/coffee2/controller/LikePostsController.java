package com.example.coffee2.controller;


import com.example.coffee2.reponsitory.LikePostsRepository;
import com.example.coffee2.request.EquipmentRequest;
import com.example.coffee2.request.LikePostsRequest;
import com.example.coffee2.request.PostsRequest;
import com.example.coffee2.response.EquipmentResponse;
import com.example.coffee2.response.LikePostsResponse;
import com.example.coffee2.response.base.ApiBaseResponse;
import com.example.coffee2.service.coffeeBean.CoffeeBeanService;
import com.example.coffee2.service.likePosts.LikePostsService;
import com.example.coffee2.utils.Constants;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Log4j2
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping(path = "api/LikePosts")
public class LikePostsController {
    @Autowired
    private LikePostsRepository repository;

    @Autowired
    private LikePostsService likePostsService;

    @PostMapping("/search")
    public ApiBaseResponse getListLikePosts(@RequestBody LikePostsRequest request) {
        List<LikePostsResponse> listResult = likePostsService.getListLikePosts(request);
        log.info("listResult | LikePosts | search: " + listResult);
        Long count = likePostsService.getCountListLikePosts(request);
        ApiBaseResponse apiBaseResponse = new ApiBaseResponse();
        apiBaseResponse.setData(listResult);
        apiBaseResponse.setOptional(count);
        return apiBaseResponse;
    }

    @PostMapping("/update")
    public ApiBaseResponse isLike(@RequestBody LikePostsRequest request) {
        ApiBaseResponse apiBaseResponse = new ApiBaseResponse();
        boolean rs = likePostsService.isLike(request);
        if(!rs) {
            apiBaseResponse.setErrorCode(Constants.CALL_API_CODE_FAIL);
            apiBaseResponse.setErrorDescription("Xảy ra lỗi khi thích bài viết");
            apiBaseResponse.setData(request);
            apiBaseResponse.setOptional(1L);
            return apiBaseResponse;
        }
        apiBaseResponse.setErrorCode(Constants.CALL_API_CODE_SUCCESS);
        apiBaseResponse.setErrorDescription("Thích bài viết thành công");
        apiBaseResponse.setData(request);
        apiBaseResponse.setOptional(1L);
        return apiBaseResponse;
    }

}
