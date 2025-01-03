package com.example.QuartzSchedule.config;

import org.quartz.CronScheduleBuilder;
import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.example.QuartzSchedule.QuartzJob.DailyEmailJob;

@Configuration
public class QuartzSchedulerConfig {


    @Bean
    public JobDetail dailyEmailJobDetail() {
        return JobBuilder.newJob(DailyEmailJob.class)
                .withIdentity("dailyEmailJob", "email-jobs")
                .storeDurably()
                .build();
    }

    @Bean
    public Trigger dailyEmailJobTrigger(JobDetail dailyJobDetail) {
        return TriggerBuilder.newTrigger()
                .forJob(dailyJobDetail)
                .withIdentity("dailyEmailTrigger", "email-triggers")
                .withSchedule(CronScheduleBuilder.dailyAtHourAndMinute(9, 0)) // Gửi lúc 9h sáng mỗi ngày
                .build();
    }

}

