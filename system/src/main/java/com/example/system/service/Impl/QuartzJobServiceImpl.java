package com.example.system.service.Impl;

import com.example.system.mapper.QuartzJobMapper;
import com.example.system.model.QuartzJob;
import com.example.system.service.QuartzJobService;
import lombok.extern.slf4j.Slf4j;
import org.quartz.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@Slf4j
public class QuartzJobServiceImpl implements QuartzJobService {

    @Autowired
    private QuartzJobMapper quartzJobMapper;

    @Autowired
    private Scheduler scheduler;


    @Override
    public List<QuartzJob> getAllQuartzJobs() {
        return quartzJobMapper.getAllQuartzJobs();
    }

    @Override
    public QuartzJob getQuartzJobById(Long jobId) {
        return quartzJobMapper.getQuartzJobById(jobId);
    }

    @Override
    public int addQuartzJob(QuartzJob quartzJob) {
        return quartzJobMapper.addQuartzJob(quartzJob);
    }

    @Override
    public int updateQuartzJob(QuartzJob quartzJob) {
        return quartzJobMapper.updateQuartzJob(quartzJob);
    }

    @Override
    public int deleteQuartzJob(Long jobId) {
        return quartzJobMapper.deleteQuartzJob(jobId);
    }


    @Override
    public void execute(QuartzJob quartzJob) throws Exception {
        // 定义任务调度实例, 并与TestJob绑定
        JobDetail jobDetail = JobBuilder.newJob((Class<? extends Job>) Class.forName(quartzJob.getBeanName()))
                .withIdentity("testJob", "testJobGroup")
                .build();
        // 定义触发器
        Trigger trigger = TriggerBuilder.newTrigger()
                .withIdentity("testTrigger", "testTriggerGroup")
                .startNow()
                .withSchedule(CronScheduleBuilder.cronSchedule(quartzJob.getCronExpression()))
                .build();

        // 使用触发器调度任务的执行
        scheduler.scheduleJob(jobDetail, trigger);
        // 开启任务
        scheduler.start();

    }


    @Override
    public void pauseJob(QuartzJob quartzJob) throws SchedulerException {
        JobKey jobKey = JobKey.jobKey(quartzJob.getJobName(), "testJobGroup"); // 获取任务的唯一标识
        scheduler.pauseJob(jobKey); // 暂停任务
    }

}