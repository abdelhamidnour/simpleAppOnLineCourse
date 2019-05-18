package com.simpleApp.services;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.simpleApp.dao.CourseDao;
import com.simpleApp.dao.RoleDao;
import com.simpleApp.dao.StudentCourseDao;
import com.simpleApp.dao.UserDao;
import com.simpleApp.dto.FormRegisterDto;
import com.simpleApp.dto.JwtResponse;
import com.simpleApp.dto.LoginUser;
import com.simpleApp.dto.ResponseMessage;
import com.simpleApp.model.Course;
import com.simpleApp.model.Role;
import com.simpleApp.model.StudentCourse;
import com.simpleApp.model.User;
import com.simpleApp.security.UserPrinciple;
import com.simpleApp.security.jwt.JwtProvider;

@Service
public class AppService {

	@Autowired
	CourseDao courseDao;
	@Autowired
	StudentCourseDao studentCourseDao;
	@Autowired
	UserDao userdao;
	@Autowired
	RoleDao roledao;
	@Autowired
	AuthenticationManager authenticationManager;
	@Autowired
	JwtProvider jwtProvider;
	@Autowired
	PasswordEncoder encoder;
	private static final Logger logger = LoggerFactory.getLogger(AppService.class.getName());

	public ResponseEntity<?> loginUser(LoginUser loginReq) {
		
		Authentication authentication = authenticationManager
				.authenticate(new UsernamePasswordAuthenticationToken(loginReq.getUsername(), loginReq.getPassword()));
		SecurityContextHolder.getContext().setAuthentication(authentication);
		String jwt = jwtProvider.generateJwtToken(authentication);
		UserPrinciple userDetails = (UserPrinciple) authentication.getPrincipal();
		return ResponseEntity.ok(new JwtResponse(jwt, userDetails.getEmail(), userDetails.getAuthorities()));
	}

	public  ResponseEntity<?>  register(FormRegisterDto registerRequest) {
		logger.info("inside register service");
		if (userdao.existsByEmail(registerRequest.getEmail())) {
			return new ResponseEntity<>(new ResponseMessage("Fail -> Email is already in use!"),
					HttpStatus.BAD_REQUEST);
		}
		// Creating user's account
		User user =  new User(registerRequest.getEmail(),encoder.encode(registerRequest.getPassword()),
				registerRequest.getUsername(),registerRequest.getDob(),registerRequest.getGender(),true);
		Role role = roledao.findByRole("ROLE_USER");
		Set<Role> roles = new HashSet<>();
		roles.add(role);
		user.setRoles(roles);
		userdao.save(user);
		return new ResponseEntity<>(new ResponseMessage("User registered successfully!"), HttpStatus.OK);

	}

	public List<Course> findAll() {
		return courseDao.findAll();
	}
	
	
	public StudentCourse registerCourse(Course registerCourseRq) {
		Course course = courseDao.findById(registerCourseRq.getCourseId()).get();
		User student = userdao.findByUsername(((User)registerCourseRq.getStudents().toArray()[0]).getUsername()).get();
		StudentCourse sc  = new StudentCourse();
		sc.setCourse(course);
		sc.setStudent(student);
		return studentCourseDao.save(sc);
	}

	public void unRegisterCourse(Course registerCourseRq) {
		Course course = courseDao.findById(registerCourseRq.getCourseId()).get();
		User student = userdao.findByUsername(((User)registerCourseRq.getStudents().toArray()[0]).getUsername()).get();
		StudentCourse sc  = new StudentCourse();
		sc.setCourse(course);
		sc.setStudent(student);
		 studentCourseDao.delete(sc);
	}

	public void addCourse(Course course) {
		courseDao.save(course);
	}

	public Course findCourse(String cousreID) {
		return courseDao.findById(cousreID).get();
	}

	public List<StudentCourse> getStudentCourses(String StudentId) {
		return studentCourseDao.findByStudentId(StudentId);
	}
}
