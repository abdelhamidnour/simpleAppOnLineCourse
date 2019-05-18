package com.simpleApp.controler;

import java.util.List;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.google.common.base.Preconditions;
import com.simpleApp.dto.FormRegisterDto;
import com.simpleApp.dto.LoginUser;
import com.simpleApp.model.Course;
import com.simpleApp.model.StudentCourse;
import com.simpleApp.services.AppService;

@RestController
@CrossOrigin()
public class AppController {
	private static final Logger logger = LoggerFactory.getLogger(AppController.class);

	@Autowired
	public AppService appService;

	@RequestMapping(value = "/auth/login", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.POST)
	public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginUser loginRequest) {
		return appService.loginUser(loginRequest);
	}

	@RequestMapping(value = "/auth/register", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.POST)
	public  ResponseEntity<?>  submitUser(@Valid @RequestBody FormRegisterDto user) {
		
		try {
			Preconditions.checkNotNull(user);
		} catch (NullPointerException ex) {
			logger.error("request body  is missing ", ex);
		}
		try {
			Preconditions.checkNotNull(user.getUsername());
		} catch (NullPointerException ex) {
			logger.error("user name  is missing ", ex);
			// throw custome exception
		}
		try {
			Preconditions.checkNotNull(user.getEmail());
		} catch (NullPointerException ex) {
			logger.error("email  is missing ", ex);
			// throw custome exception
		}
		try {
			Preconditions.checkNotNull(user.getDob());
		} catch (NullPointerException ex) {
			logger.error("dob is missing ", ex);
			// throw custome exception
		}
		try {
			Preconditions.checkNotNull(user.getPassword());
		} catch (NullPointerException ex) {
			logger.error("password  is missing ", ex);
			// throw custome exception
		}try {
			Preconditions.checkNotNull(user.getGender());
		} catch (NullPointerException ex) {
			logger.error("gender  is missing ", ex);
			// throw custome exception
		}

		return appService.register(user);
	}

	@RequestMapping("/access-denied")
	public String showAccessDenied() {
		logger.info("inside access-denied");
		return "access-denied";

	}

	@RequestMapping(value = "/availableCourses", produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.GET)
	@PreAuthorize("hasRole('USER')")
	public List<Course> getAvailableCourses() {
		logger.info("inside getAvailableCourses");
		List<Course> courses = null;
		try {
			courses = appService.findAll();
		} catch (Exception e) {
			// TODO: handle exception
		}
		return courses;
	}

	
	@RequestMapping(value = "/course/{id}", produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.GET)
	@PreAuthorize("hasRole('USER')")
	public ResponseEntity<Course> getCourse(@PathVariable String id) {
		logger.info("inside getCourse with id " + id);
		return new ResponseEntity<Course>(appService.findCourse(id), HttpStatus.OK);
	}

	@RequestMapping(value = "/registerCourse", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.POST)
	@PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
	public ResponseEntity<StudentCourse> registerCourse(@RequestBody Course cousreRequest) {
		logger.info("inside registerCourse");
		return new ResponseEntity<StudentCourse>(appService.registerCourse(cousreRequest), HttpStatus.OK);
	}
	@RequestMapping(value = "/unregisterCourse", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.POST)
	@PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
	public void unregisterCourse(@RequestBody Course cousreRequest) {
		logger.info("inside registerCourse");
		appService.unRegisterCourse(cousreRequest);
	}
	@RequestMapping(value = "/", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.POST)
	@PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
	public void addCourse(@RequestBody Course addRequest) {
		logger.info("inside addCourse ");
		appService.addCourse(addRequest);
	}
	@RequestMapping(value = "/getStudentCourses/{userId}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.GET)
	@PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
	public List<StudentCourse> getStudentCourses(@PathVariable String userId) {
		logger.info("inside getStudentCourses");
		return appService.getStudentCourses(userId);
	}
}
