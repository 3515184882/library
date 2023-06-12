package com.bjpowernode.service.impl;

import com.bjpowernode.mapper.UserMapper;
import com.bjpowernode.pojo.Book;
import com.bjpowernode.pojo.PageResult;
import com.bjpowernode.pojo.UserManaging;
import com.bjpowernode.service.UserService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    UserMapper userMapper;


    @Override
    public PageResult selectUser(UserManaging userManaging, Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum,pageSize);
        Page<UserManaging> page = userMapper.selectUser(userManaging);
        return new PageResult(page.getTotal(),page.getResult());
    }

    @Override
    public Integer addUser(UserManaging userManaging) {
        return userMapper.addUser(userManaging);
    }

    @Override
    public UserManaging findById(String uId) {
        return userMapper.findById(Integer.valueOf(uId));
    }

    @Override
    public Integer editUser(UserManaging userManaging) {
        UserManaging b = this.findById(userManaging.getuId()+"");
//        //将登录密码设置在userManaging对象中
//        userManaging.setuPassword(b.getuPassword());
//        //将用户角色设置在userManaging对象中
//        userManaging.setuRole(b.getuRole());
        return userMapper.editUser(userManaging);
    }

    @Override
    public Integer leave(UserManaging userManaging) {
        return userMapper.leave(userManaging);
    }


}
