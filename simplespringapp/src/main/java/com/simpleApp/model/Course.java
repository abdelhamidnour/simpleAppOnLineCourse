package com.simpleApp.model;

import java.text.SimpleDateFormat;
import java.util.Set;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Document(collection = "course")
public class Course {
	@Id
	private String courseId;
	private String courseName;
	private String description;
	private String publishString;
	private String lastUpString;
	private Long totalHours;
	private String instructor;
	@Field
	private boolean registered = false;
	@DBRef
	private Set<User> students;
	

	public Set<User> getStudents() {
		return students;
	}

	public void setStudents(Set<User> students) {
		this.students = students;
	}

	public String getCourseId() {
		return courseId;
	}

	public void setCourseId(String courseId) {
		this.courseId = courseId;
	}

	public String getCourseName() {
		return courseName;
	}

	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getPublishString() {
		return publishString;
	}

	public void setPublishString(String publishString) {
		this.publishString = publishString;
	}

	public String getLastUpString() {
		return lastUpString;
	}

	public void setLastUpString(String lastUpString) {
		this.lastUpString = lastUpString;
	}

	public Long getTotalHours() {
		return totalHours;
	}

	public void setTotalHours(Long totalHours) {
		this.totalHours = totalHours;
	}

	public String getInstructor() {
		return instructor;
	}

	public void setInstructor(String instructor) {
		this.instructor = instructor;
	}

	public boolean isRegistered() {
		return registered;
	}

	public void setRegistered(boolean registered) {
		this.registered = registered;
	}


}
