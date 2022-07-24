package com.avature.service;


import com.avature.entity.Job;
import com.avature.entity.User;
import com.avature.repository.UserRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.util.List;

@Service
@Slf4j
public class EmailService {

    public EmailService(@Value(value = "${spring.mail.username}") String sender, UserRepository userRepository, JavaMailSender javaMailSender) {
        this.sender = sender;
        this.userRepository = userRepository;
        this.javaMailSender = javaMailSender;
    }

    String sender;
    String HOST = "http://localhost:8080/jobs/";
    UserRepository userRepository;
    JavaMailSender javaMailSender;

    @Async
    public void sendEmailWithDetails(Job job){
        List<User> usersList;
        if( job.getPreferredSkills() != null && !job.getPreferredSkills().isEmpty())
            usersList = userRepository.findBySkills(job.getPreferredSkills());
        else{
            usersList = userRepository.findAll();
        }
        for(User user: usersList){
            try {
                generateEmailBodyAndSend(job, user);
            }catch (MessagingException e){
                log.error("error while sending mail to user:"+user.getEmail());
            }
        }
    }

    private void generateEmailBodyAndSend(Job job, User user ) throws MessagingException {
        String body = "<h4>Hey, Found new matching job for you</h4>\n <a href="+HOST+job.getId()+">"+job.getTitle()+"</a>";
        MimeMessage msg = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(msg, true);
        helper.setText(body, true);
        helper.setSubject("Job Notification");
        helper.setFrom(sender);
        helper.setTo(user.getEmail());
        log.info("sending mail to:"+user.getEmail());
        javaMailSender.send(msg);
        log.info("sent mail to:"+user.getEmail());
    }
}
