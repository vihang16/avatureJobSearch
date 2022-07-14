package com.avature.controller;

import com.avature.entity.Job;
import com.avature.exception.InvalidJobException;
import com.avature.service.JobService;
import lombok.AllArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/jobs")
public class JobController {

    JobService jobService;

    @PostMapping("/add")
    public Job addJobListing(@Valid @RequestBody Job job){
        return jobService.addJobPosting(job);
    }

    @GetMapping("/all")
    public List<Job> getAllJobs(@RequestParam(value = "number", defaultValue = "0") Integer pageNumber,
                                @RequestParam( value = "size", defaultValue = "10") Integer pageSize,
                                @RequestParam(value = "title", required = false) String title,
                                @RequestParam(value = "company", required = false) String company,
                                @RequestParam(value = "minSalary", required = false) @Min(0) Integer minSalary,
                                @RequestParam(value = "maxSalary", required = false ) @Max(99999) Integer maxSalary,
                                @RequestParam(value = "contains", required = false ) String description,
                                @RequestParam(value = "skills", required = false) List<String> preferredSkills ){
        return jobService.retrieveAllJobs(pageNumber, pageSize, title, minSalary, maxSalary, description, preferredSkills, company);
    }

    @GetMapping("/{id}")
    public Job getJobById(@PathVariable("id") String id) throws InvalidJobException {
        return jobService.retrieveJobById(id);
    }
}
