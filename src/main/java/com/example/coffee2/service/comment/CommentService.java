package com.example.coffee2.service.comment;

import com.example.coffee2.request.CommentRequest;
import com.example.coffee2.response.CommentResponse;

public interface CommentService {
    boolean create(CommentRequest request);

    boolean update(CommentRequest request);
}
