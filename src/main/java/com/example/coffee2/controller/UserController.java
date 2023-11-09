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
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.sql.Blob;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.Base64;
import java.util.List;
import java.util.Optional;
import java.util.zip.DataFormatException;
import java.util.zip.Deflater;
import java.util.zip.Inflater;


@RestController
@Log4j2
@CrossOrigin(origins = "*")
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

    @PostMapping("/search/{id}")
    ResponseEntity<ResponseObject> findByIdUser(@PathVariable Long id) {
        Optional<UserEntity> foundAccountNumber = repository.findById(id);
        log.info("foundAccountNumber: " + foundAccountNumber);
        return ResponseEntity.status(HttpStatus.OK).body(new ResponseObject("ok", "get AccountNumber successfully", foundAccountNumber));
    }

    @PostMapping("/create")
    public ApiBaseResponse create(@RequestBody UserRequest request) {
        ApiBaseResponse apiBaseResponse = new ApiBaseResponse();
        //List<String>
        List<UserEntity> list = repository.findByUserName(request.getUserName());
        if (list.size() > 0) {
            apiBaseResponse.setErrorCode(Constants.CALL_API_CODE_FAIL);
            apiBaseResponse.setErrorDescription("Tài khoản đã tồn tại trong hệ thống");
            apiBaseResponse.setData(request);
            apiBaseResponse.setOptional(1l);
            return apiBaseResponse;
        }
        boolean rs = userService.create(request);
        if (!rs) {
            apiBaseResponse.setErrorCode(Constants.CALL_API_CODE_FAIL);
            apiBaseResponse.setErrorDescription("Tạo mới tài khoản Không thành công");
            apiBaseResponse.setData(request);
            apiBaseResponse.setOptional(1L);
            return apiBaseResponse;
        }
        apiBaseResponse.setErrorCode(Constants.CALL_API_CODE_SUCCESS);
        apiBaseResponse.setErrorDescription("Tạo mới tài khoản thành công");
        apiBaseResponse.setData(request);
        apiBaseResponse.setOptional(1L);
        log.info("response: " + request);
        return apiBaseResponse;
    }

    // compress the image bytes before storing it in the database
//    public static byte[] compressBytes(byte[] data) {
//        Deflater deflater = new Deflater();
//        deflater.setInput(data);
//        deflater.finish();
//
//        ByteArrayOutputStream outputStream = new ByteArrayOutputStream(data.length);
//        byte[] buffer = new byte[1024];
//        while (!deflater.finished()) {
//            int count = deflater.deflate(buffer);
//            outputStream.write(buffer, 0, count);
//        }
//        try {
//            outputStream.close();
//        } catch (IOException e) {
//        }
//        System.out.println("Compressed Image Byte Size - " + outputStream.toByteArray().length);
//
//        return outputStream.toByteArray();
//    }
//
//    // uncompress the image bytes before returning it to the angular application
//    public static byte[] decompressBytes(byte[] data) {
//        Inflater inflater = new Inflater();
//        inflater.setInput(data);
//        ByteArrayOutputStream outputStream = new ByteArrayOutputStream(data.length);
//        byte[] buffer = new byte[1024];
//        try {
//            while (!inflater.finished()) {
//                int count = inflater.inflate(buffer);
//                outputStream.write(buffer, 0, count);
//            }
//            outputStream.close();
//        } catch (IOException ioe) {
//        } catch (DataFormatException e) {
//        }
//        return outputStream.toByteArray();
//    }

//    @PostMapping("/upload")
//    public ResponseEntity.BodyBuilder uploadImage(@RequestPart("imageFile") MultipartFile file) throws IOException {
//        log.info("file: " + file);
////        UserEntity img = new UserEntity(file.getOriginalFilename(), file.getContentType(),
////                compressBytes(file.getBytes()));
//        UserEntity img = new UserEntity(compressBytes(file.getBytes()));
//        repository.save(img);
//        return ResponseEntity.status(HttpStatus.OK);
//    }

//    @PostMapping(path = "/create", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
//    public ResponseEntity<ResponseObject> create(@RequestParam("file") MultipartFile file) throws IOException, SQLException {
//        log.info("getResource | user/create: " + file.getResource());
//        log.info("getBytes | user/create: " + file.getBytes());
////        log.info("getBytes | user/create: " + Base64.getDecoder().decode(file.getBytes()));
//        byte[] bytes = file.getBytes();
//        Blob blob = new javax.sql.rowset.serial.SerialBlob(bytes);
//
//        UserEntity obj = new UserEntity();
//        obj.setImage(file.getOriginalFilename());
//        log.info("blob: " + blob);
////        obj.setData(blob.toString());
////        obj.setData(Base64.getDecoder().decode(file.getBytes()));
//        repository.save(obj);
//        return ResponseEntity.status(HttpStatus.OK).body(
//                new ResponseObject("ok", "get full product successfully", obj)
//        );
//
//    }

//    @GetMapping(path = { "/get/{userName}" })
//    public UserEntity getImage(@PathVariable("userName") String userName) throws IOException {
//
//        final Optional<UserEntity> retrievedImage = repository.findByUserName1(userName);
//        UserEntity img = new UserEntity(decompressBytes(retrievedImage.get().getData()));
//        return img;
//    }


    @PostMapping("/update")
    public ApiBaseResponse update(@RequestBody UserRequest request) {
        ApiBaseResponse apiBaseResponse = new ApiBaseResponse();
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

    @PostMapping("/updateInfo")
    public ApiBaseResponse updateInfo(@RequestBody UserRequest request) {
        ApiBaseResponse apiBaseResponse = new ApiBaseResponse();
        boolean rs = userService.updateInfo(request);
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
