package com.example.QuartzSchedule.QuartzJob;

import java.nio.charset.StandardCharsets;
import java.util.List;

import org.quartz.JobExecutionContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.mail.MailProperties;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.quartz.QuartzJobBean;
import org.springframework.stereotype.Component;

import com.example.QuartzSchedule.entity.User;
import com.example.QuartzSchedule.repository.UserRepository;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;

@Component
public class DailyEmailJob extends QuartzJobBean {

    @Autowired
    private JavaMailSender javaMailSender;

    @Autowired
    private MailProperties mailProperties;

    @Autowired
    private UserRepository userRepository; // Repository để lấy danh sách email từ DB

    @Override
    protected void executeInternal(JobExecutionContext context) {
        List<User> users = userRepository.findAll(); // Lấy tất cả user từ cơ sở dữ liệu

        for (User user : users) {
            sendMail(mailProperties.getUsername(), user.getEmail(), "Daily Notification", "This is your daily message!");
        }
    }

    private void sendMail(String fromMail, String toEmail, String subject, String body) {
        try {
            MimeMessage message = javaMailSender.createMimeMessage();
            MimeMessageHelper messageHelper = new MimeMessageHelper(message, StandardCharsets.UTF_8.toString());
            messageHelper.setSubject(subject);
            messageHelper.setText(body, true);
            messageHelper.setFrom(fromMail);
            messageHelper.setTo(toEmail);

            javaMailSender.send(message);
        } catch (MessagingException e) {
            System.out.println(e);
        }
    }
}

