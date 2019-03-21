package com.example.quertz.controller;

import com.example.quertz.entity.Quertz;
import com.example.quertz.quertz.trigger.MyScheduler;
import com.example.quertz.service.IQuertzService;
import org.quartz.CronExpression;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @Author: lqk
 * @Date: 2019/3/19 14:48
 * @Version: 1.0
 */
@RestController
@RequestMapping("quertz")
public class QuertzController {

    private static final Integer TASK_STATUS_STOP = 0;    //任务状态 停止
    private static final Integer TASK_STATUS_STARTR = 1;  //任务状态 正常执行


    @Autowired
    private IQuertzService quertzServiceImpl;
    @Autowired
    private MyScheduler myScheduler;

    //新增任务(并开启)
    @Transactional
    @PostMapping("add")
    public String addQuertz(@RequestBody Quertz quertz){
        if(null == quertz.getJobName() || null == quertz.getJobGroup()){
            return "任务名称或组名为空";
        }
        if (!CronExpression.isValidExpression(quertz.getCronExpression())) {
            return "cron表达式格式不正确";
        }
        if(null!=quertzServiceImpl.getOneQuertz(quertz.getJobName(),quertz.getJobGroup())){
            return "任务已存在";
        }
        quertzServiceImpl.addJob(quertz);  //数据库中保存任务
        myScheduler.startJob(quertz);     //开启这个任务
        return "success";
    }


    //删除已有任务
    @Transactional
    @PostMapping("delete")
    public String deleteQuertz(@RequestBody Quertz quertz){
        myScheduler.deleteJob(quertz);                  //删除正在执行的任务
        quertzServiceImpl.delete(quertz.getTaskId());   //删除数据库的任务
        return "success";
    }

    //修改某个任务
//    @Transactional
//    @PostMapping("update")
//    public String updateQuertz(@RequestBody  Quertz quertz){
//        if(!CronExpression.isValidExpression(quertz.getCronExpression())){
//            return "cron表达式格式不正确";
//        }
//        return "success";
//    }

    //暂停某个定时任务
    @Transactional
    @PostMapping("suspended")
    public String suspendedQuertz(@RequestBody Quertz quertz){
        myScheduler.suspendedJob(quertz);                //暂停正在执行的任务
        quertzServiceImpl.updateJobStatus(TASK_STATUS_STOP,quertz.getJobName(),quertz.getJobGroup());//修改数据库的job状态
        return "success";
    }

    //暂停所有任务
    @PostMapping("pauseAll")
    public String pauseAllJob(){
        myScheduler.pauseAllJob();
        return "success";
    }

    //恢复某个定时任务
    @PostMapping("restore")
    public String restoreQuertz(@RequestBody Quertz quertz){
        myScheduler.restoreJob(quertz);
        quertzServiceImpl.updateJobStatus(TASK_STATUS_STARTR,quertz.getJobName(),quertz.getJobGroup()); //修改数据库任务状态
        return "success";
    }

    //列表所有任务
    @RequestMapping("select")
    public List<Quertz> selectQuertz(){
        return quertzServiceImpl.getAll();
    }

}
