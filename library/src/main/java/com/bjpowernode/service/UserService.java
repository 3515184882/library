package com.bjpowernode.service;

import com.bjpowernode.pojo.Book;
import com.bjpowernode.pojo.PageResult;
import com.bjpowernode.pojo.UserManaging;

public interface UserService {

    PageResult selectUser(UserManaging userManaging, Integer pageNum, Integer pageSize);

    Integer addUser(UserManaging userManaging);

    UserManaging findById(String uId);

    //编辑人员信息
    Integer editUser(UserManaging userManaging);

    //离职人员
    Integer leave(UserManaging userManaging);
}
