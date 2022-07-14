package com.avature.service;

import com.avature.entity.Job;
import com.avature.exception.InvalidJobException;
import com.avature.repository.JobRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
@Transactional
@AllArgsConstructor
public class JobService {

    JobRepository jobRepository;

    MongoTemplate mongoTemplate;
    EmailService emailService;

    public Job addJobPosting(Job job){
         log.info("going to save job details:"+job);
         Job savedJob = jobRepository.save(job);
         emailService.sendEmailWithDetails(savedJob);
         return savedJob;
    }

    public List<Job> retrieveAllJobs(int number, int size, String title, Integer minSalary, Integer maxSalary, String description, List<String> preferredSkills, String company){
        var query = new Query().with(Pageable.ofSize(size));
        List<Criteria> criteriaList = new ArrayList<>();
        if( title != null && !title.isBlank())
            criteriaList.add(Criteria.where("title").regex(title, "i"));
        if( preferredSkills != null && !preferredSkills.isEmpty())
            criteriaList.add(Criteria.where("preferredSkills").in(preferredSkills));
        if(description != null  && !description.isBlank())
            criteriaList.add(Criteria.where("description").regex(description, "i"));
        if(maxSalary != null && maxSalary > 0)
            criteriaList.add(Criteria.where("maxSalary").lte(maxSalary));
        if(minSalary != null && minSalary > 0)
           criteriaList.add(Criteria.where("minSalary").gte(minSalary));
        if(company != null && !company.isBlank())
            criteriaList.add(Criteria.where("company").regex(company, "i"));
        if(!criteriaList.isEmpty())
            query.addCriteria(new Criteria().andOperator(criteriaList));
        log.info("query to execute in db:"+query);
        List<Job> jobs =mongoTemplate.find(query, Job.class);
        log.info("found total jobs:"+jobs.size());
        return jobs;
    }

    public Job retrieveJobById(String id) throws InvalidJobException {
        log.info("retrieving job by id:"+id);
        return jobRepository.findById(id).orElseThrow( () -> new InvalidJobException("job id does not exist"));
    }

}
