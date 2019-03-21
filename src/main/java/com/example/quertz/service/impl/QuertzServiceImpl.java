package com.example.quertz.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.example.quertz.entity.Quertz;
import com.example.quertz.mapper.QuertzMapper;
import com.example.quertz.service.IQuertzService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author: lqk
 * @Date: 2019/3/20 13:51
 * @Version: 1.0
 */
@Service
public class QuertzServiceImpl implements IQuertzService {



    @Autowired
    private QuertzMapper quertzMapper;

    @Override
    public void addJob(Quertz quertz) {
        quertzMapper.insert(quertz);
    }

    @Override
    public void delete(Long id) {
        quertzMapper.deleteById(id);
    }

    @Override
    public void update(Quertz quertz) {
        quertzMapper.updateById(quertz);
    }

    @Override
    public List<Quertz> getAll() {
        return quertzMapper.selectList(null);
    }


    @Override
    public void updateJobStatus(Integer taskStatus ,String jobName, String jobGroup) {
        quertzMapper.updateJobStatus(taskStatus,jobName,jobGroup);
    }


    /**
     * @param jobName
     * @param jobGroup  selectOne方法必须保证数据库只有一条数据的情况下才能使用，否则会报错。建议使用selectList()
     * @return
     */
    @Override
    public Quertz getOneQuertz(String jobName, String jobGroup) {
        LambdaQueryWrapper<Quertz> eq = new QueryWrapper<Quertz>().lambda().eq(Quertz::getJobName, jobName).eq(Quertz::getJobGroup, jobGroup);
        return  quertzMapper.selectOne(eq);
    }
}
