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
    private String content1;
    private Long status;
    private String imagePath;
    private Long userId;
    private String createdAt;
    private String updatedAt;

    private int pageIndex;
    private int pageSize;
}
