package com.bjpowernode.interceptor;

import com.bjpowernode.pojo.User;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ResourcesInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request,
                             HttpServletResponse response, Object handler) throws Exception {
        //是否登录过的判断
        if ( request.getSession().getAttribute("USER_SESSION") ==null){
            //此时没有登录，打回到登录页面并给出提示
            request.getSession().setAttribute("msg","您还没有登录，请先登录");
            request.getRequestDispatcher("/admin/login.jsp").forward(request,response);
            return false;
        }

        return true;//放行请求

    }

}
