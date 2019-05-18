package com.simpleApp.dao;


import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.simpleApp.model.Course;
@Repository
public interface CourseDao extends MongoRepository<Course,String>{
}
