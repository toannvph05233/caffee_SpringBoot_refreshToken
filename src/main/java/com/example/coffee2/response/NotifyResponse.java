package com.example.coffee2.response;

import lombok.Data;

@Data
public class NotifyResponse {
    private Long id;
    private String fromUser;
    private String toUser;
    private Long isNotify;
    private String name;
    private String postId;
    private String commentId;
}
