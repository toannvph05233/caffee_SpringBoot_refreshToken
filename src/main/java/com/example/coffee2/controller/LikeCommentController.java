package com.example.coffee2.controller;

import com.example.coffee2.request.LikeCommentRequest;
import com.example.coffee2.request.LikePostsRequest;
import com.example.coffee2.response.base.ApiBaseResponse;
import com.example.coffee2.service.LikeComment.LikeCommentService;
import com.example.coffee2.utils.Constants;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Log4j2
@RequestMapping(path = "api/like-comment")
public class LikeCommentController {
    @Autowired
    private LikeCommentService likeCommentService;

    @PostMapping("/create")
    public ApiBaseResponse isLikeComment(@RequestBody LikeCommentRequest request) {
        ApiBaseResponse apiBaseResponse = new ApiBaseResponse();
        boolean rs = likeCommentService.update(request);
        if (!rs) {
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
