package com.example.coffee2.controller;

import com.example.coffee2.dto.UserDto;
import com.example.coffee2.entity.ResponseObject;
import com.example.coffee2.entity.UserEntity;
import com.example.coffee2.reponsitory.UserRespository;
import com.example.coffee2.request.PostsRequest;
import com.example.coffee2.request.UserRequest;
import com.example.coffee2.response.UserResponse;
import com.example.coffee2.response.base.ApiBaseResponse;
import com.example.coffee2.service.user.UserService;
import com.example.coffee2.utils.Constants;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Log4j2
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping(path = "api/user")
public class UserController {
    @Autowired
    private UserRespository repository;

    @Autowired
    private UserService userService;

    @GetMapping("")
    ResponseEntity<ResponseObject> findAllCoffeeBean() {
        List<UserEntity> foundProduct = repository.findAllUser();
        return ResponseEntity.status(HttpStatus.OK).body(
                new ResponseObject("ok", "get full product successfully", foundProduct)
        );
    }

    @PostMapping("/search")
    public ApiBaseResponse getListUser(@RequestBody UserRequest request) {
        List<UserResponse> listResult = userService.getListUser(request);
        Long count = userService.getCountListUser(request);
        ApiBaseResponse apiBaseResponse = new ApiBaseResponse();
        apiBaseResponse.setData(listResult);
        apiBaseResponse.setOptional(count);
        return apiBaseResponse;
    }

    @PostMapping("/create")
    public ApiBaseResponse create(@RequestBody UserRequest request) {
        ApiBaseResponse apiBaseResponse = new ApiBaseResponse();
        List<String> list = repository.findByUserName(request.getUserName());
        if (list.size() > 0) {
            apiBaseResponse.setErrorCode(Constants.CALL_API_CODE_FAIL);
            apiBaseResponse.setErrorDescription("Bài viết đã tồn tại trong hệ thống");
            apiBaseResponse.setData(request);
            apiBaseResponse.setOptional(1l);
            return apiBaseResponse;
        }
        boolean rs = userService.create(request);
        if (!rs) {
            apiBaseResponse.setErrorCode(Constants.CALL_API_CODE_FAIL);
            apiBaseResponse.setErrorDescription("Tạo mới bài viết Không thành công");
            apiBaseResponse.setData(request);
            apiBaseResponse.setOptional(1L);
            return apiBaseResponse;
        }
        apiBaseResponse.setErrorCode(Constants.CALL_API_CODE_SUCCESS);
        apiBaseResponse.setErrorDescription("Tạo mới bài viết thành công");
        apiBaseResponse.setData(request);
        apiBaseResponse.setOptional(1L);
        log.info("response: " + request);
        return apiBaseResponse;
    }


    @PostMapping("/update")
    public ApiBaseResponse updatePosts(@RequestBody UserRequest request) {
        ApiBaseResponse apiBaseResponse = new ApiBaseResponse();
//        List<String> list = repository.findByTitle(request.getTitle());
//        if (list.size() > 0) {
//            apiBaseResponse.setErrorCode(Constants.CALL_API_CODE_FAIL);
//            apiBaseResponse.setOptional(1l);
//            apiBaseResponse.setErrorDescription("Bài viết đã tồn tại trong hệ thống");
//            apiBaseResponse.setData(request);
//            return apiBaseResponse;
//        }
        boolean rs = userService.update(request);
        if (!rs) {
            apiBaseResponse.setErrorCode(Constants.CALL_API_CODE_FAIL);
            apiBaseResponse.setErrorDescription("Cập nhật bài viết không thành công");
            apiBaseResponse.setData(request);
            apiBaseResponse.setOptional(1l);
            return apiBaseResponse;

        }
        apiBaseResponse.setErrorCode(Constants.CALL_API_CODE_SUCCESS);
        apiBaseResponse.setErrorDescription("Cập nhật bài viết thành công");
        apiBaseResponse.setData(request);
        apiBaseResponse.setOptional(1l);
        return apiBaseResponse;
    }

    @PostMapping("/delete")
    public ApiBaseResponse delete(@RequestBody UserRequest request) {
        ApiBaseResponse apiBaseResponse = new ApiBaseResponse();
        boolean rs = userService.delete(request);
        if (!rs) {
            apiBaseResponse.setErrorCode(Constants.CALL_API_CODE_FAIL);
            apiBaseResponse.setErrorDescription("Xóa bài viết không thành công");
            apiBaseResponse.setData(request);
            apiBaseResponse.setOptional(1l);
            return apiBaseResponse;

        }
        apiBaseResponse.setErrorCode(Constants.CALL_API_CODE_SUCCESS);
        apiBaseResponse.setErrorDescription("Xóa bài viết thành công");
        apiBaseResponse.setData(request);
        apiBaseResponse.setOptional(1l);
        return apiBaseResponse;
    }

//    @PostMapping(path = "/save")
//    public String saveUser(@RequestBody UserDto userDto) {
//        String id = userService.addUser(userDto);
//        return id;
//    }
}
