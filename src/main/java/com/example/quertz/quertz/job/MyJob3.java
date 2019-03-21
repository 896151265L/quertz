package com.example.quertz.quertz.job;

import com.example.quertz.service.impl.QuertzServiceImpl;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.quartz.QuartzJobBean;

/**
 * @Author: lqk
 * @Date: 2019/3/20 15:29  处理自己的定时作业
 * @Version: 1.0
 */
public class MyJob3 extends QuartzJobBean {


    @Autowired
    private QuertzServiceImpl quertzService;


    @Override
    protected void executeInternal(JobExecutionContext context) throws JobExecutionException {


        //做自己的业务任务
        System.out.println("========33333333333333========");
    }
}
