package com.example.quertz.service;

import com.example.quertz.entity.Quertz;

import java.util.List;

/**
 * @Author: lqk
 * @Date: 2019/3/19 15:00
 * @Version: 1.0
 */
public interface IQuertzService {


    void addJob(Quertz quertz);     //新增任务
    void delete(Long id);           //删除任务
    void update(Quertz quertz);     //修改任务
    List<Quertz> getAll();          //列表所有任务

    //修改任务状态
    void updateJobStatus(Integer taskStatus ,String jobName,String jobGroup);
    //新增定时任务时查询是否存在
    Quertz getOneQuertz(String jobName,String jobGroup);
}
