package com.bjpowernode.controller;

import com.bjpowernode.pojo.PageResult;
import com.bjpowernode.pojo.Record;
import com.bjpowernode.pojo.User;
import com.bjpowernode.service.RecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/record")
public class RecordController {
    @Autowired
    private RecordService recordService;

    @RequestMapping("/searchRecords")
    private ModelAndView searchRecords(Record record, HttpServletRequest request,Integer pageNum,Integer pageSize){
        if (pageNum==null){
            pageNum=1;
        }
        if (pageSize==null){
            pageSize=5;
        }
        //获取当前登录用户信息
        User user =(User) request.getSession().getAttribute("USER_SESSION");
        PageResult pageResult = recordService.searchRecords(record, user, pageNum, pageSize);
        ModelAndView modelAndView =new ModelAndView();
        modelAndView.setViewName("record");
        //将查询的数据存放到ModelAndView的对象中
        modelAndView.addObject("pageResult",pageResult);
        //将查询到的参数返回到页面，用于回显到查询的输入框中
        modelAndView.addObject("search",record);
        //将当前页码返回到页面，用于分页插件的分页显示
        modelAndView.addObject("pageNum",pageNum);
        //将当前查询的控制器路径返回到页面，页码变化时继续向该路径发送请求
        modelAndView.addObject("gourl",request.getRequestURI());
        return modelAndView;

    }
}
