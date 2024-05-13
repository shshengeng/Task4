package com.example.system.mapper;


import com.example.system.model.QuartzJob;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface QuartzJobMapper {

    @Select("SELECT * FROM sys_quartz_job")
    List<QuartzJob> getAllQuartzJobs();

    @Select("SELECT * FROM sys_quartz_job WHERE job_id = #{jobId}")
    QuartzJob getQuartzJobById(Long jobId);

    @Insert("INSERT INTO sys_quartz_job (bean_name, cron_expression, is_pause, job_name, method_name, params, description, person_in_charge, email, sub_task, pause_after_failure, create_by, update_by, create_time, update_time) " +
            "VALUES (#{beanName}, #{cronExpression}, #{isPause}, #{jobName}, #{methodName}, #{params}, #{description}, #{personInCharge}, #{email}, #{subTask}, #{pauseAfterFailure}, #{createBy}, #{updateBy}, #{createTime}, #{updateTime})")
    int addQuartzJob(QuartzJob quartzJob);

    @Update("UPDATE sys_quartz_job SET bean_name = #{beanName}, cron_expression = #{cronExpression}, job_name = #{jobName}, is_pause = #{isPause}, method_name = #{methodName}, params = #{params}, description = #{description}, " +
            "person_in_charge = #{personInCharge}, email = #{email}, sub_task = #{subTask}, pause_after_failure = #{pauseAfterFailure}, update_by = #{updateBy}, update_time = #{updateTime} " +
            "WHERE job_id = #{jobId}")
    int updateQuartzJob(QuartzJob quartzJob);

    @Delete("DELETE FROM sys_quartz_job WHERE job_id = #{jobId}")
    int deleteQuartzJob(Long jobId);


}
