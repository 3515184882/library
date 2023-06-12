package com.bjpowernode.service.impl;

import com.bjpowernode.mapper.AdminMapper;
import com.bjpowernode.pojo.User;
import com.bjpowernode.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AdminServiceImpl implements AdminService {

    @Autowired
    AdminMapper adminMapper;

    @Override
    public User login(User user) {
        return adminMapper.login(user);
    }
}
