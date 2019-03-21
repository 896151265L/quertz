package com.example.quertz.quertz.trigger;

import com.example.quertz.entity.Quertz;
import com.example.quertz.quertz.job.MyJob1;
import com.example.quertz.quertz.job.MyJob2;
import com.example.quertz.quertz.job.MyJob3;
import com.example.quertz.service.IQuertzService;
import lombok.SneakyThrows;
import org.quartz.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;
import org.springframework.stereotype.Component;

import static org.quartz.CronScheduleBuilder.cronSchedule;


/**
 * @Author: lqk
 * @Date: 2019/3/6 16:11
 * @Version: 1.0  quertz调度器   quertz持久化需要使用quertz提供的11张表 官方下载
 */
@Component
public class MyScheduler {

    private static final String  MY_JOB_ONE = "com.example.quertz.quertz.job.MyJob1";
    private static final String  MY_JOB_TWO = "com.example.quertz.quertz.job.MyJob2";
    private static final String  MY_JOB_THREE = "com.example.quertz.quertz.job.MyJob3";
    private static final Integer TASK_STATUS = 1;       //任务状态 执行中



    @Autowired
    private SchedulerFactoryBean schedulerFactoryBean;
    @Autowired
    private IQuertzService quertzService;


    //开启定时任务
    @SneakyThrows
    public void startJob(Quertz quertz){
        //获取调度器scheduler
        Scheduler scheduler = schedulerFactoryBean.getScheduler();
        //启动
        scheduler.start();
        //创建JobDetail实例
        JobDetail build = null;

        //根据传入任务实现类分发任务
        if(MY_JOB_ONE.endsWith(quertz.getClazz())){
            build = JobBuilder.newJob(MyJob1.class)
                    .withIdentity(quertz.getJobName(),quertz.getJobGroup())
                    .build();
        }
        if(MY_JOB_TWO.endsWith(quertz.getClazz())){
            build = JobBuilder.newJob(MyJob2.class)
                    .withIdentity(quertz.getJobName(),quertz.getJobGroup())
                    .build();
        }
        if(MY_JOB_THREE.endsWith(quertz.getClazz())){
            build = JobBuilder.newJob(MyJob3.class)
                    .withIdentity(quertz.getJobName(),quertz.getJobGroup())
                    .build();
        }

        /**
         * TODO...
         * Trigger触发器可以根据任务需求进行分发管理
         */
        //构建Trigger实例
        CronTrigger trigger = TriggerBuilder.newTrigger()
                .startNow() //立即生效
                .withSchedule(cronSchedule(quertz.getCronExpression()))
                .build();

        //4、执行
        scheduler.scheduleJob(build, trigger);
        //修改任务状态为启用
        quertzService.updateJobStatus(TASK_STATUS,quertz.getJobName(),quertz.getJobGroup());

    }



    //暂停某个定时任务(可支持多个)
    @SneakyThrows
    public String suspendedJob(Quertz quertz){
        JobKey jobKey = new JobKey(quertz.getJobName(),quertz.getJobGroup());
        Scheduler scheduler = schedulerFactoryBean.getScheduler();
        JobDetail schedulerFactoryBean = scheduler.getJobDetail(jobKey);
        if(null == schedulerFactoryBean){
            return "任务未开启";
        }
        scheduler.pauseJob(jobKey);
        return "success";
    }

    //暂停所有任务
    @SneakyThrows
    public String pauseAllJob(){
        Scheduler scheduler = schedulerFactoryBean.getScheduler();
        scheduler.pauseAll();
        return "success";
    }

    //恢复某个定时任务
    @SneakyThrows
    public String restoreJob(Quertz quertz){

        JobKey jobKey = new JobKey(quertz.getJobName(), quertz.getJobGroup());
        Scheduler scheduler = schedulerFactoryBean.getScheduler();
        JobDetail jobDetail = scheduler.getJobDetail(jobKey);
        if(null!=jobDetail){
            scheduler.resumeJob(jobKey);
        }
        return "success";
    }


    //删除一个任务(可支持多个)
    @SneakyThrows
    public String deleteJob(Quertz quertz){
        JobKey jobKey = new JobKey(quertz.getJobName(), quertz.getJobGroup());
        if(null != jobKey){
            schedulerFactoryBean.getScheduler().deleteJob(jobKey);
        }
        return "success";
    }

}
