package com.bjpowernode.mapper;

import com.bjpowernode.pojo.Book;
import com.bjpowernode.pojo.UserManaging;
import com.github.pagehelper.Page;

public interface UserMapper {
    //查询人员
    Page<UserManaging> selectUser(UserManaging userManaging);
    //新增人员
    Integer addUser(UserManaging userManaging);
    //编辑人员信息
    Integer editUser(UserManaging userManaging);
    //根据id查询人员信息
    UserManaging findById(Integer uId);

    //离职人员
    Integer leave(UserManaging userManaging);



}
