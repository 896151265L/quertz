package com.example.quertz.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.quertz.entity.Quertz;
import org.apache.ibatis.annotations.Param;

/**
 * @Author: lqk
 * @Date: 2019/3/20 13:47
 * @Version: 1.0
 */
public interface QuertzMapper extends BaseMapper<Quertz> {

    //修改任务状态
    void updateJobStatus(@Param("taskStatus") Integer taskStatus , @Param("jobName") String jobName , @Param("jobGroup") String jobGroup);
}
