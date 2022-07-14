package com.avature.entity;


import com.mongodb.lang.NonNull;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.boot.context.properties.bind.DefaultValue;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import java.util.List;
import java.util.Map;

@Document(collection = "Job")
@Setter
@Getter
@ToString
public class Job {

    @Id
    String id;
    @NotEmpty(message = "job title can not be empty")
    String title;
    String company;
    List<String> qualifications;
    String description;
    @Min(value = 0, message = "min salary can not less than 0")
    Integer minSalary;
    List<String> preferredSkills;
    Integer maxSalary;
    Map<String, String> additionalParams;
}
