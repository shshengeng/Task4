package com.example.system.service;

import com.example.system.model.QuartzJob;
import org.apache.ibatis.annotations.*;
import org.quartz.SchedulerException;

import java.util.List;

public interface QuartzJobService {


    List<QuartzJob> getAllQuartzJobs();

    QuartzJob getQuartzJobById(Long jobId);

    int addQuartzJob(QuartzJob quartzJob);

    int updateQuartzJob(QuartzJob quartzJob);

    int deleteQuartzJob(Long jobId);

    void execute(QuartzJob quartzJob) throws Exception;

    void pauseJob(QuartzJob quartzJob) throws SchedulerException;

}
