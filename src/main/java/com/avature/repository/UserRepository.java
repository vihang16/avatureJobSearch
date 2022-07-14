package com.avature.repository;

import com.avature.entity.User;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface UserRepository extends MongoRepository<User, String> {
     List<User> findBySkills(List<String> skills);
}
