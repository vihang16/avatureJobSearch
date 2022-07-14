package com.avature.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotBlank;
import java.util.List;

@Document(collection = "User")
@Setter
@Getter
@ToString
public class User {

    @Id
    String id;

    @NotBlank(message = "first name can not be empty")
    String firstName;
    @NotBlank(message = "last name can not be empty")
    String lastName;
    @NotBlank(message = "email can not be empty")
    String email;
    List<String> skills;
}
