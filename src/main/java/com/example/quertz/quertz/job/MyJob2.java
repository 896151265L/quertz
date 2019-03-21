package com.example.quertz.quertz.job;

import com.example.quertz.service.impl.QuertzServiceImpl;
import org.quartz.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.quartz.QuartzJobBean;



/**
 * @Author: lqk
 * @Date: 2019/3/6 16:07 处理自己的定时作业
 * @Version: 1.0
 */
public class MyJob2 extends QuartzJobBean {



    @Autowired
    private QuertzServiceImpl quertzService;


    @Override
    protected void executeInternal(JobExecutionContext context) throws JobExecutionException {


        System.out.println("========22222222222222222========");
    }
}
