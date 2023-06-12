package com.bjpowernode.controller;

import com.bjpowernode.pojo.PageResult;
import com.bjpowernode.pojo.Result;
import com.bjpowernode.pojo.UserManaging;
import com.bjpowernode.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.jws.WebParam;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/users")
public class UserController {
    @Autowired
    UserService userService;

    @RequestMapping("/search")
    public ModelAndView search(UserManaging userManaging, Integer pageNum, Integer pageSize, HttpServletRequest request){
        if (pageNum==null){
            pageNum =1;
        }
        if (pageSize == null){
            pageSize = 5;
        }
        //查询到的用户
        PageResult pageResult = userService.selectUser(userManaging, pageNum, pageSize);
        ModelAndView modelAndView=new ModelAndView();
        modelAndView.setViewName("user");

        modelAndView.addObject("pageResult",pageResult);
        modelAndView.addObject("search",userManaging);
        modelAndView.addObject("pageNum",pageNum);
        modelAndView.addObject("gourl",request.getRequestURI());
        return modelAndView;

    }

    @RequestMapping("/addUser")
    @ResponseBody
    public Result addUser(UserManaging userManaging){
        try {
            Integer count = userService.addUser(userManaging);
            if (count!=1){
                return new Result(false,"新增人员失败！");
            }
            return new Result(true,"新增人员成功");
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false,"新增人员失败！");
        }
    }

    @RequestMapping("/findById")
    @ResponseBody
    public Result<UserManaging> findById(String uId){
        try {
            UserManaging userManaging = userService.findById(uId);
            if (userManaging==null){
                return new Result(false,"查询人员失败!");
            }
            return new Result(true,"查询人员成功",userManaging);
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false,"查询人员失败！");
        }
    }

    @RequestMapping("/editUser")
    @ResponseBody
    public Result editUser(UserManaging userManaging){
        //根据人员的id和人员名进行修改
        try {
            Integer count = userService.editUser(userManaging);
            if (count!=1){
                return new Result(false,"修改失败！");
            }
            return new Result(true,"修改成功！");
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false,"修改失败！");
        }

    }

    @RequestMapping("/delUser")
    @ResponseBody
    public Result delUser(UserManaging userManaging){
        try {
            Integer leave = userService.leave(userManaging);
            System.out.println(leave);
            if (leave!=1){
                return new Result(false,"离职失败！");
            }
            return new Result(true,"离职成功！");
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false,"离职失败！");
        }
    }





}
