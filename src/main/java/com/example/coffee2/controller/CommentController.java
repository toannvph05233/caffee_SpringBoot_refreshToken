package com.example.coffee2.controller;

import com.example.coffee2.request.CommentRequest;
import com.example.coffee2.request.EquipmentRequest;
import com.example.coffee2.response.base.ApiBaseResponse;
import com.example.coffee2.service.comment.CommentService;
import com.example.coffee2.utils.Constants;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Log4j2
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping(path = "api/comment")
public class CommentController {
    @Autowired
    private CommentService commentService;

    @PostMapping("/create")
    public ApiBaseResponse create(@RequestBody CommentRequest request){
        ApiBaseResponse apiBaseResponse = new ApiBaseResponse();

        boolean rs = commentService.create(request);
        if(!rs) {
            apiBaseResponse.setErrorCode(Constants.CALL_API_CODE_FAIL);
            apiBaseResponse.setErrorDescription("Thêm mới bình luận không thành công");
            apiBaseResponse.setData(request);
            apiBaseResponse.setOptional(1L);
            return apiBaseResponse;
        }
        apiBaseResponse.setErrorCode(Constants.CALL_API_CODE_SUCCESS);
        apiBaseResponse.setErrorDescription("Thêm mới bình luận thành công");
        apiBaseResponse.setData(request);
        apiBaseResponse.setOptional(1L);
        return apiBaseResponse;
    }
}
