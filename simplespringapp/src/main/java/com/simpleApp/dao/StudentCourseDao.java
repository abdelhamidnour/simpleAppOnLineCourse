package com.simpleApp.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.simpleApp.model.StudentCourse;
@Repository
public interface StudentCourseDao extends MongoRepository<StudentCourse,String>{
	List<StudentCourse> findByStudentId(String StudentId);

}
