package com.example.quertz.quertz.job;

import com.example.quertz.service.impl.QuertzServiceImpl;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.quartz.QuartzJobBean;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @Author: lqk
 * @Date: 2019/3/20 15:22  处理自己的定时作业
 * @Version: 1.0
 */
public class MyJob1 extends QuartzJobBean {




    @Autowired
    private QuertzServiceImpl quertzService;

    private static final String date = "yyyy-MM-dd HH:mm:ss";



    @Override
    protected void executeInternal(JobExecutionContext context) throws JobExecutionException {

        String format = new SimpleDateFormat(date).format(new Date());

        //做自己的业务任务
        System.out.println("========111111111111111111111111======>"+ format);
    }
}
