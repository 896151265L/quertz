package com.example.quertz;

import com.alibaba.fastjson.JSON;
import com.example.quertz.controller.QuertzController;
import com.example.quertz.entity.Quertz;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.List;


@RunWith(SpringRunner.class)
@SpringBootTest
public class QuertzApplicationTests {

    @Autowired
    private QuertzController quertzController;

    @Test
    public void contextLoads() {

//        Quertz quertz = new Quertz();
//        quertz.setJobName("任务2");
//        quertz.setJobGroup("分组2");
//        quertz.setCronExpression("0 59 12 * * ?");
//        quertz.setClazz("com.example.quertz.quertz.job.MyJob2");
//        quertz.setTaskDescription("这是第二个测试任务");
//
//
//        String json = "{\"clazz\":\"com.example.quertz.quertz.job.MyJob2\",\"cronExpression\":\"0 59 12 * * ?\",\"jobGroup\":\"分组2\",\"jobName\":\"任务2\",\"taskCreateTime\":\"2019-03-22T15:43:01.634\",\"taskDescription\":\"这是第二个测试任务\",\"taskStatus\":0}";
//
//        Quertz quertz1 = JSON.parseObject(json, Quertz.class);
//        System.out.println(quertz1);


        List<String> strings = Arrays.asList("");
    }

}
