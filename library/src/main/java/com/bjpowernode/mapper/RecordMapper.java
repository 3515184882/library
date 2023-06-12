package com.bjpowernode.mapper;

import com.bjpowernode.pojo.Record;
import com.github.pagehelper.Page;

public interface RecordMapper {
    //新增借阅记录
    Integer addRecord(Record record);

    //查询借阅记录
    Page<Record> searchRecords(Record record);
}
