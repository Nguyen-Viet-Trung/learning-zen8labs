package com.example.QuartzSchedule.QuartzJob;

import java.nio.charset.StandardCharsets;

import org.quartz.JobDataMap;
import org.quartz.JobExecutionContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.mail.MailProperties;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.quartz.QuartzJobBean;
import org.springframework.stereotype.Component;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
@Component
public class EmailJob extends QuartzJobBean{
    @Autowired
    private JavaMailSender javaMailSender;

    @Autowired
    private MailProperties mailProperties;

    @Override
    protected void executeInternal(JobExecutionContext context)  {
        JobDataMap jobDataMap = context.getMergedJobDataMap(); 
        String subject = jobDataMap.getString("subject");
        String body = jobDataMap.getString("body");
        String recipientEmail = jobDataMap.getString("email");
        //extract information used for email sent
        sendMail(mailProperties.getUsername(), recipientEmail, subject, body); 

}
    private void sendMail(String fromMail, String toEmail, String subject, String body){
        try {
            MimeMessage message = javaMailSender.createMimeMessage();
            MimeMessageHelper messageHelper = new MimeMessageHelper(message, StandardCharsets.UTF_8.toString());
            messageHelper.setSubject(subject);
            messageHelper.setText(body,true);
            messageHelper.setFrom(fromMail);
            messageHelper.setTo(toEmail);

            javaMailSender.send(message);
        } catch (MessagingException e) {
            System.out.println(e);
        }
    }
}
