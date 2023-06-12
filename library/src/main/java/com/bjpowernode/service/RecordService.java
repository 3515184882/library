package com.bjpowernode.service;

import com.bjpowernode.pojo.PageResult;
import com.bjpowernode.pojo.Record;
import com.bjpowernode.pojo.User;

import javax.jws.soap.SOAPBinding;

/**
 * 借阅记录接口
 */
public interface RecordService {
    //新增借阅记录
    Integer addRecord(Record record);

    //查询借阅记录
    PageResult searchRecords(Record record, User user,Integer pageNum,Integer pageSize);
}
