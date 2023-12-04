package com.example.coffee2.reponsitory.Customer.impl;

import com.example.coffee2.reponsitory.Customer.NotifyCustomer;
import com.example.coffee2.request.LikePostsRequest;
import com.example.coffee2.request.NotifyRequest;
import com.example.coffee2.response.LikePostsResponse;
import com.example.coffee2.response.NotifyResponse;
import com.example.coffee2.utils.FunctionUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@Repository
public class NotifyCustomerImpl implements NotifyCustomer {
    @Autowired
    private final EntityManager entityManager;

    public NotifyCustomerImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public List<NotifyResponse> getListNotify(NotifyRequest request) {
        try {
            StringBuilder sql = new StringBuilder();
            Map<String, Object> params = new HashMap<>();
            createSqlGetListNotify(request, sql, params, false);
            Query query = entityManager.createNativeQuery(sql.toString());
            if (params.size() > 0) {
                params.forEach((key, value) -> {
                    query.setParameter(key, value);
                });
            }
            return FunctionUtils.mapping(query.getResultList(), NotifyResponse.class);
        } catch (Exception e) {
            log.error("error1: " + e.getMessage());
        }
        return null;
    }

    private void createSqlGetListNotify(NotifyRequest request, StringBuilder sql, Map<String, Object> params, boolean isCount) {
        if (!isCount) {
            sql.append("select \n");
            sql.append("f.id, \n");
            sql.append("f.user_id, \n");
            sql.append("f.post_id, \n");
            sql.append("f.comment_id, \n");
            if (request.getIsRelyComment() == 1) {
                sql.append("posts.image_path, posts.category, user1.name, ");
            }
//            if (request.getIsComment() == 1) {
//                sql.append("posts.image_path, posts.category, user1.name, ");
//            }
            sql.append("f.create_at \n");
            sql.append("from \n");
            sql.append("notify f \n");
            if (request.getIsRelyComment() == 1) {
                sql.append("join posts on f.post_id = posts.id join user1 on f.user_id = user1.id");
            }
            sql.append(" where 1=1 ");
        }
    }
}
