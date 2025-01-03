package com.example.QuartzSchedule.web;

import java.time.ZonedDateTime;
import java.util.Date;
import java.util.UUID;

import javax.validation.Valid;

import org.quartz.JobBuilder;
import org.quartz.JobDataMap;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.SimpleScheduleBuilder;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.QuartzSchedule.QuartzJob.EmailJob;
import com.example.QuartzSchedule.payload.EmailRequest;
import com.example.QuartzSchedule.payload.EmailResponse;

import lombok.extern.slf4j.Slf4j;
@Slf4j
@RestController
public class EmailSchedulerController {

    @Autowired
    private Scheduler scheduler;

    @PostMapping("/schedule/email")
    public ResponseEntity<EmailResponse> scheduleEmail(@Valid @RequestBody EmailRequest emailRequest){
        try { //xử lý nếu thuộc tính thời gian được đưa vào là quá khứ
            ZonedDateTime dateTime = ZonedDateTime.of(emailRequest.getDateTime(), emailRequest.getTimeZone()); //xử lý chuyển đổi thời gian theo khu vực
            if(dateTime.isBefore(ZonedDateTime.now())){
                EmailResponse response = new EmailResponse(false,"Try again! Scheduled time is in past");
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
            }
            JobDetail jobDetail = buildJobDetail(emailRequest); // đầu vào từ request body
            Trigger trigger = buildTrigger(jobDetail, dateTime);
            scheduler.scheduleJob(jobDetail, trigger);
            EmailResponse response = new EmailResponse(true,jobDetail.getKey().getName(),jobDetail.getKey().getGroup(),"Email Scheduled");
            response.setJobId(jobDetail.getKey().getName());
            response.setJobGroup(jobDetail.getKey().getGroup());
            return ResponseEntity.ok(response); //trả về trạng thái sau khi request
            }
        catch (SchedulerException e) {
            log.error("Error while scheduling email", e);
            EmailResponse response = new EmailResponse(false,"Try again! Error while Scheduling");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }
    @GetMapping("/get")
    public ResponseEntity<String> getApi(){
        return ResponseEntity.ok("Passed");
    }
    private JobDetail buildJobDetail(EmailRequest emailRequest){ // định nghĩa công việc cần hoàn thành, lưu giữ công việc cần hoàn thành
        JobDataMap jobDataMap = new JobDataMap();

        jobDataMap.put("email", emailRequest.getEmail());
        jobDataMap.put("subject", emailRequest.getSubject());
        jobDataMap.put("body", emailRequest.getBody());
        
        return JobBuilder.newJob(EmailJob.class) //Quartz requires a job class to be explicitly associated with the JobDetail.
        .withIdentity(UUID.randomUUID().toString(),"email-jobs")
        .withDescription("Send Email Job")
        .usingJobData(jobDataMap)
        .storeDurably()
        .build();
    }
    private Trigger buildTrigger(JobDetail jobDetail, ZonedDateTime startAt){ //định nghĩa thời điểm, điều kiện để trigger công việc
        return TriggerBuilder.newTrigger()
        .forJob(jobDetail)
        .withIdentity(jobDetail.getKey().getName(), "email-triggers")
        .withDescription("Sent email trigger")
        .withSchedule(SimpleScheduleBuilder.simpleSchedule().withMisfireHandlingInstructionFireNow())
        .startAt(Date.from(startAt.toInstant()))
        .build();
    }
}
