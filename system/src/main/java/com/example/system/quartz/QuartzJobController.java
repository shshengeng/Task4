package com.example.system.quartz;


import com.example.common.response.ResponseResult;
import com.example.system.model.QuartzJob;
import com.example.system.service.QuartzJobService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@Slf4j
@Tag(name = "定时任务接口")
@RestController
@RequestMapping("/sys//quartzJob")
public class QuartzJobController {

    @Autowired
    private QuartzJobService quartzJobService;
    @Autowired
    private Scheduler scheduler;


    @Operation(summary = "查询所有", description = "查询所有")
    @GetMapping("")
    public ResponseResult queryAll(){
        List<QuartzJob> allQuartzJobs = quartzJobService.getAllQuartzJobs();
        if(allQuartzJobs!=null){
            return new ResponseResult<>(200, "find quartz jobs success", allQuartzJobs, System.currentTimeMillis());
        }
        return new ResponseResult<>(200, "find quartz jobs failed", null, System.currentTimeMillis());
    }


    @Operation(summary = "通过id查询", description = "通过id查询")
    @GetMapping("{jobId}")
    public ResponseResult queryById(@PathVariable Long jobId) {
        QuartzJob quartzJobById = quartzJobService.getQuartzJobById(jobId);
        if (quartzJobById == null) {
            return new ResponseResult<>(500, "find quartz job failed", null, System.currentTimeMillis());
        }
        return new ResponseResult<>(200, "find quartz job success", null, System.currentTimeMillis());
    }


    @Operation(summary = "添加定时任务", description = "添加定时任务")
    @PostMapping("")
    public ResponseResult add(@RequestBody QuartzJob quartzJob) {
        int i = quartzJobService.addQuartzJob(quartzJob);
        if (i > 0) {
            return new ResponseResult<>(200, "add task success", null, System.currentTimeMillis());
        }
        return new ResponseResult<>(500, "add task failed", null, System.currentTimeMillis());
    }


    @Operation(summary = "更新定时任务", description = "更新定时任务")
    @PutMapping("")
    public ResponseResult edit(@RequestBody QuartzJob quartzJob) {
        int i = quartzJobService.updateQuartzJob(quartzJob);
        if (i > 0) {
            return new ResponseResult<>(200, "edit task success", null, System.currentTimeMillis());
        }
        return new ResponseResult<>(500, "edit task failed", null, System.currentTimeMillis());
    }


    @Operation(summary = "通过id删除", description = "通过id删除")
    @DeleteMapping("{jobId}")
    public ResponseResult delete(@PathVariable Long jobId) {
        int i = quartzJobService.deleteQuartzJob(jobId);
        if (i > 0) {
            return new ResponseResult<>(200, "delete task success", null, System.currentTimeMillis());
        }
        return new ResponseResult<>(500, "delete task failed", null, System.currentTimeMillis());
    }


    @Operation(summary = "立即执行", description = "立即执行")
    @GetMapping("/execute/{jobId}")
    public ResponseResult execute(@PathVariable Long jobId) {
        QuartzJob quartzJobById = quartzJobService.getQuartzJobById(jobId);
        if (quartzJobById == null) {
            return new ResponseResult<>(500, "find quartz job failed", null, System.currentTimeMillis());
        }
        try {
            quartzJobService.execute(quartzJobById);
        } catch (Exception e) {
            //e.printStackTrace();
            log.info("定时任务 立即执行失败>>"+e.getMessage());
            return new ResponseResult<>(500, "execute task failed", null, System.currentTimeMillis());
        }
        return new ResponseResult<>(200, "execute task success", null, System.currentTimeMillis());
    }

    @GetMapping("/pause/{jobId}")
    @Operation(summary = "停止定时任务")
    public ResponseResult pauseJob(@PathVariable Long jobId) throws SchedulerException {
        QuartzJob quartzJobById = quartzJobService.getQuartzJobById(jobId);
        if (quartzJobById == null) {
            return new ResponseResult<>(500, "find quartz job failed", null, System.currentTimeMillis());
        }
        quartzJobService.pauseJob(quartzJobById);
        return new ResponseResult<>(200, "pause job success", null, System.currentTimeMillis());
    }
}