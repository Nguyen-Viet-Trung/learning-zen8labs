package com.example.QuartzSchedule.config;

import org.quartz.CronScheduleBuilder;
import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.example.QuartzSchedule.QuartzJob.DailyEmailJob;

import jakarta.annotation.PostConstruct;

@Configuration
public class QuartzSchedulerConfig {

    @Autowired
    private Scheduler scheduler;

    @Bean
    public JobDetail dailyEmailJobDetail() {
        return JobBuilder.newJob(DailyEmailJob.class)
                .withIdentity("dailyEmailJob", "email-jobs")
                .storeDurably()
                .build();
    }

    @Bean
    public Trigger dailyEmailJobTrigger() {
        return TriggerBuilder.newTrigger()
                .forJob(dailyEmailJobDetail())
                .withIdentity("dailyEmailTrigger", "email-triggers")
                .withSchedule(CronScheduleBuilder.dailyAtHourAndMinute(9, 0)) // Gửi lúc 9h sáng mỗi ngày
                .build();
    }

    @PostConstruct
    public void scheduleDailyEmailJob() throws SchedulerException {
        scheduler.scheduleJob(dailyEmailJobDetail(), dailyEmailJobTrigger());
    }
}

