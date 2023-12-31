package com.bjpowernode.controller;

import com.bjpowernode.pojo.User;
import com.bjpowernode.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/user")
public class AdminController {
    @Autowired
    AdminService adminService;

    @RequestMapping("/login")
    public String login(User user, HttpServletRequest request){
        try {
            User u = adminService.login(user);
            /**
             * 用户账号和密码是否查询出用户信息
             * 是：将用户信息存入Session,并跳转到后台首页
             * 否：Request域中添加提示信息，并转发到登录页面
             */
            if (u!=null){
                request.getSession().setAttribute("USER_SESSION",u);
                return "redirect:/admin/main.jsp";
            }
            else {
                request.setAttribute("msg","用户名或密码错误");
                return "forward:/admin/login.jsp";
            }
        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("msg","系统错误");
            return "forward:/admin/login.jsp";
        }
    }

    @RequestMapping("/logout")
    public String logout(HttpServletRequest request){
        try {
            HttpSession session = request.getSession();

            //销毁session
            session.invalidate();
            return "forward:/admin/login.jsp";
        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("msg","系统错误");
            return "forward:/admin/login.jsp";
        }
    }

}
