package com.example.coffee2.request;

import lombok.Data;

import java.util.Date;

@Data
public class PostsRequest {
    private Long id;
    private Long like1;
    private Long share;
    private Long comment;
    private String title;
    private String contentPost;
    private String contentDetail;
    private Long status;
    private String imagePath;
    private Long userId;
    private String createdAt;
    private String updatedAt;
    private String hashTag;

    private int pageIndex;
    private int pageSize;
}
