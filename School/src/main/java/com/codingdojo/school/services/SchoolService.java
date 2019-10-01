package com.codingdojo.school.services;

import java.util.List;
import java.util.Optional;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.codingdojo.school.models.Course;
import com.codingdojo.school.models.Student;
import com.codingdojo.school.models.StudentCourse;
import com.codingdojo.school.repositories.CourseRepo;
import com.codingdojo.school.repositories.StudentCourseRepo;
import com.codingdojo.school.repositories.StudentRepo;
import com.codingdojo.school.validator.UserValidator;

@Service
public class SchoolService {
	@Autowired
	private StudentRepo sRepo;
	@Autowired
	private CourseRepo cRepo;
	@Autowired
	private StudentCourseRepo scRepo;
	@Autowired
	public UserValidator userValidator;
	
	// register user and hash their password
    public Student registerStudent(Student  student) {
        String hashed = BCrypt.hashpw(student.getPassword(), BCrypt.gensalt());
        student.setPassword(hashed);
        return sRepo.save(student);
    }
    
    // find user by email
    public Student findByEmail(String email) {
        return sRepo.findByEmail(email);
    }
    
    // find user by id
    public Student findStudentById(Long id) {
    	Optional<Student> u = sRepo.findById(id);
    	
    	if(u.isPresent()) {
            return u.get();
    	} else {
    	    return null;
    	}
    }
    
    // authenticate user
    public boolean authenticateStudent(String email, String password) {
        // first find the user by email
    	Student student = sRepo.findByEmail(email);
        // if we can't find it by email, return false
        if(student == null) {
            return false;
        } else {
            // if the passwords match, return true, else, return false
            if(BCrypt.checkpw(password, student.getPassword())) {
                return true;
            } else {
                return false;
            }
        }
    }

	public Student save(Student student) {
		return sRepo.save(student);
	}

	public void saveCourse(Course c) {
		cRepo.save(c);
	}

	public Student getName(Student u) {
		return getName(u);
	}

	public List<Course> findAllCourses() {
		return cRepo.findAll();
	}

	public StudentCourse joinStudentToCourse(StudentCourse sc) {
		return scRepo.save(sc);
	}

	public Course findCourseById(Long id) {
		Optional<Course> c = cRepo.findById(id);
    	if(c.isPresent()) {
            return c.get();
    	} else {
    	    return null;
    	}
	}

	public void remove(Long userId, Long courseId) {
		Long id = scRepo.getStudentCourse(userId, courseId).getId();
		scRepo.deleteById(id);
	}

	public void deleteCourseById(Long id) {
		Optional<Course> c = cRepo.findById(id);
		if(c.isPresent()) {
			cRepo.deleteById(id);
    	}
	}
	
	public void deleteUserFromEvent(Long userId, Long courseId) {
		Long id = scRepo.getStudentCourse(userId, courseId).getId();
		scRepo.deleteById(id);
	}
}
