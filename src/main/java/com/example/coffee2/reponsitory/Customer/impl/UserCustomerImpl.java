package com.example.coffee2.reponsitory.Customer.impl;

import com.example.coffee2.reponsitory.Customer.UserCustomer;
import com.example.coffee2.request.UserRequest;
import com.example.coffee2.response.UserResponse;
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
public class UserCustomerImpl implements UserCustomer {
    @Autowired
    private final EntityManager entityManager;

    public UserCustomerImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public List<UserResponse> getListUser(UserRequest request) {
        try {
            StringBuilder sql = new StringBuilder();
            Map<String, Object> params = new HashMap<>();
            createSqlGetListUser(request, sql, params, false);
            Query query = entityManager.createNativeQuery(sql.toString());
            if (params.size() > 0) {
                params.forEach((key, value) -> {
                    query.setParameter(key, value);
                });
            }
            if (request.getPageIndex() != 0 && request.getPageSize() != 0) {
                query.setFirstResult((request.getPageIndex() - 1) * request.getPageSize());
                query.setMaxResults(request.getPageSize());
            }
            return FunctionUtils.mapping(query.getResultList(), UserResponse.class);
        } catch (Exception e) {
            log.error("error1: " + e.getMessage());
        }
        return null;
    }

    public Long getCountListUser(UserRequest request) {
        try {
            StringBuilder sql = new StringBuilder();
            Map<String, Object> params = new HashMap<>();
            createSqlGetListUser(request, sql, params, true);
            Query query = entityManager.createNativeQuery(sql.toString());
            if (params.size() > 0) {
                params.forEach((key, value) -> {
                    query.setParameter(key, value);
                });
            }

            Long count = ((Integer) query.getSingleResult()).longValue();
            log.info("getCountListPosts | count  " + count);
            return count;
        } catch (Exception e) {
            log.error("error2: " + e.getMessage());
        }
        return null;
    }

    private void createSqlGetListUser(UserRequest request, StringBuilder sql, Map<String, Object> params, boolean isCount) {
        if (isCount) {
            sql.append("select count(*) \n");
        } else {
//            sql.append("select f.* \n");
            sql.append("select \n");
            sql.append("f.id, \n");
            sql.append("f.user_name, \n");
            sql.append("f.password, \n");
            sql.append("f.email, \n");
            sql.append("f.first_name, \n");
            sql.append("f.last_name, \n");
            sql.append("f.address, \n");
            sql.append("f.age, \n");
            sql.append("f.role, \n");
            sql.append("f.phone_number, \n");
            sql.append("f.date_of_birth, \n");
            sql.append("f.sex, \n");
            sql.append("f.create_date, \n");
            sql.append("f.status \n");
        }
        sql.append("from \n");
        sql.append("user1 f \n");
        sql.append("where f.status != -1 \n");
    }
}
