package com.bjpowernode.service.impl;

import com.bjpowernode.mapper.RecordMapper;
import com.bjpowernode.pojo.PageResult;
import com.bjpowernode.pojo.Record;
import com.bjpowernode.pojo.User;
import com.bjpowernode.service.RecordService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class RecordServiceImpl implements RecordService {

    @Autowired
    RecordMapper recordMapper;


    @Override
    public Integer addRecord(Record record) {
        String recordId = UUID.randomUUID().toString().replaceAll("-", "");
        record.setRecordId(recordId);
        return recordMapper.addRecord(record);
    }

    /**
     * 查询借阅记录
     * @param record 借阅记录的查询条件
     * @param user 当前的登录用户
     * @param pageNum 当前页码
     * @param pageSize 每页显示数量
     * @return
     */
    @Override
    public PageResult searchRecords(Record record, User user, Integer pageNum, Integer pageSize) {
        //设置分页查询的参数，开始分页
        PageHelper.startPage(pageNum,pageSize);
        //如果不是管理员，则查询条件中的借阅人设置为当前登录用户
        if (!"ADMIN".equals(user.getUserRole())){
            record.setRecordBorrower(user.getUserName());
        }
        Page<Record> page =recordMapper.searchRecords(record);
        return new PageResult(page.getTotal(),page.getResult());
    }
}
