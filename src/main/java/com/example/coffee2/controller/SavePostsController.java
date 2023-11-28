package com.example.coffee2.controller;

import com.example.coffee2.request.LikePostsRequest;
import com.example.coffee2.request.SavePostsRequest;
import com.example.coffee2.response.LikePostsResponse;
import com.example.coffee2.response.SavePostsResponse;
import com.example.coffee2.response.base.ApiBaseResponse;
import com.example.coffee2.service.savePosts.SavePostsService;
import com.example.coffee2.utils.Constants;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Log4j2
//@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping(path = "api/save-posts")
public class SavePostsController {
    @Autowired
    private SavePostsService savePostsService;

    @PostMapping("/search")
    public ApiBaseResponse getListSavePosts(@RequestBody SavePostsRequest request) {
        List<SavePostsResponse> listResult = savePostsService.getListSavePosts(request);
        Long count = savePostsService.getCountListSavePosts(request);
        ApiBaseResponse apiBaseResponse = new ApiBaseResponse();
        apiBaseResponse.setData(listResult);
        apiBaseResponse.setOptional(count);
        return apiBaseResponse;
    }

    @PostMapping("/update")
    public ApiBaseResponse isSave(@RequestBody SavePostsRequest request) {
        ApiBaseResponse apiBaseResponse = new ApiBaseResponse();
        boolean rs = savePostsService.update(request);
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
