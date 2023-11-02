package com.example.coffee2.service.likePosts;

import com.example.coffee2.request.LikePostsRequest;
import com.example.coffee2.response.LikePostsResponse;

import java.util.List;

public interface LikePostsService {
    List<LikePostsResponse> getListLikePosts(LikePostsRequest request);

    Long getCountListLikePosts(LikePostsRequest request);

    boolean isLike(LikePostsRequest request);
}
