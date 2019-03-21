package com.example.quertz.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @Author: lqk
 * @Date: 2019/3/18 10:31
 * @Version: 1.0  定时任务
 */
@Data
@TableName("t_quertz")
public class Quertz implements Serializable {

    private static final long serialVersionUID = 8117991442508511567L;

    @TableId
    private Long taskId;
    private String jobName;                                         //任务名称
    private String jobGroup;                                        //任务组别
    private String cronExpression;                                  //任务表达式
    private String clazz;                                           //任务实现类
    private String taskDescription;                                 //任务描述
    private Integer taskStatus = 0;                                 //任务状态 1：启用 0：禁用  (默认为：0)
    private LocalDateTime taskCreateTime = LocalDateTime.now();     //任务创建时间(默认为创建时间)
    private String triggerName;                                     //触发器名称
    private String triggerGroup;                                    //触发器分组

}
