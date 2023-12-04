package com.example.coffee2.response;

import lombok.Data;

@Data
public class NotifyResponse {
    private Long id;
    private String userId;
    private String postId;
    private String commentId;
    private String createAt;
}
