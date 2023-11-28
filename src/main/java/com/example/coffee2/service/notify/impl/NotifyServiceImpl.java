package com.example.coffee2.service.notify.impl;

import com.example.coffee2.entity.NotifyEntity;
import com.example.coffee2.reponsitory.Customer.NotifyCustomer;
import com.example.coffee2.reponsitory.NotifyRepository;
import com.example.coffee2.request.NotifyRequest;
import com.example.coffee2.response.NotifyResponse;
import com.example.coffee2.service.notify.NotifyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NotifyServiceImpl implements NotifyService {

    @Autowired
    private NotifyRepository repository;

    @Autowired
    private NotifyCustomer notifyCustomer;

    @Override
    public List<NotifyResponse> getListNotify(NotifyRequest request) {
        return notifyCustomer.getListNotify(request);

    }

    @Override
    public boolean create(NotifyRequest request) {
        NotifyEntity obj = new NotifyEntity();
        obj.setFromUser(request.getFromUser());
        obj.setToUser(request.getToUser());
        obj.setIsNotify(1L);
        obj.setName(request.getName());
        obj.setPostId(request.getPostId());
        obj.setCommentId(request.getCommentId());
        repository.save(obj);
        return true;
    }

    @Override
    public boolean delete(NotifyRequest request) {
        NotifyEntity obj = repository.findByCommentId(request.getCommentId()).orElse(null);
        repository.delete(obj);
        return true;
    }
}
