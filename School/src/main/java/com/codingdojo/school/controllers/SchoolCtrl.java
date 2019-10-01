package com.codingdojo.school.controllers;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;


import com.codingdojo.school.models.Course;
import com.codingdojo.school.models.Student;
import com.codingdojo.school.models.StudentCourse;
import com.codingdojo.school.services.SchoolService;
import com.codingdojo.school.validator.UserValidator;

@Controller
public class SchoolCtrl {
	@Autowired
	private SchoolService sServ;
	@Autowired
	private UserValidator userValidator;
	
	@GetMapping("/")
	public String index(@ModelAttribute("newStudent") Student student) {
		return "index.jsp";
	}

	@PostMapping("/registration")
    public String register(@Valid @ModelAttribute("newStudent") Student student, BindingResult result, HttpSession session) {
    	userValidator.validate(student, result);
    	if(result.hasErrors()) {
    		System.out.println("error registering");
    		return "index.jsp";
		}
    	sServ.registerStudent(student);
    	session.setAttribute("currentUser", student.getId());
    	return "redirect:/courses";
    }
	
	@PostMapping("/login")
    public String login(Model model, @RequestParam("email") String email, @RequestParam("password") String password, HttpSession session) {
    	boolean exist = sServ.authenticateStudent(email, password);
    	if (exist) {
    		Student u = sServ.findByEmail(email);
    		session.setAttribute("currentUser", u.getId());
    		return "redirect:/courses";
    	} else {
    		model.addAttribute("newStudent", new Student());
    		model.addAttribute("error", "Invalid Credentials. Please try again");
    		return "index.jsp";
    	}
    }
	
	@GetMapping("/logout")
    public String logout(HttpSession session) {
    	session.invalidate();
    	return "redirect:/";
    }
	
//	ALL COURSES PAGE
	@GetMapping("/courses")
	public String courses(@ModelAttribute("midTable") StudentCourse sc, Model model, HttpSession session) {
		if(session.getAttribute("currentUser") == null) {
    		return "redirect:/";
		} else {
			Long userId = (Long) session.getAttribute("currentUser");
			Student u = sServ.findStudentById(userId);
			model.addAttribute("currentUser", u);
			model.addAttribute("allCourses", sServ.findAllCourses());
			return "courses.jsp";
		}
	}
	
	@GetMapping("courses/new")
	public String newCourses(@ModelAttribute("newCourse") Course c, Model model, HttpSession session) {
		if(session.getAttribute("currentUser") == null) {
			return "redirect:/";
		} else {
			Long userId = (Long) session.getAttribute("currentUser");
    		Student u = sServ.findStudentById(userId);
    		model.addAttribute("currentUser", u);
    		return "newCourse.jsp";
		}
	}
	
	
	@PostMapping("/courses")
	public String createdOrUpdateCourse(HttpSession session, Model model, @Valid @ModelAttribute("newCourse") Course c, BindingResult result) {
		if(session.getAttribute("currentUser") == null) {
    		return "redirect:/login";
    	} else if(result.hasErrors()) {
    		return "newCourse.jsp";
    	} else {
    		Long userId = (Long) session.getAttribute("currentUser");
        	Student u = sServ.findStudentById(userId);
        	model.addAttribute("currentUser", u);
        	sServ.saveCourse(c);
        	return "redirect:/courses";
		}
	}
	
	@PostMapping("/add")
	public String addStudentToCourse(Model model, @ModelAttribute("midTable") StudentCourse sc, HttpSession session) {
		Long userId = (Long) session.getAttribute("currentUser");
    	Student u = sServ.findStudentById(userId);
    	model.addAttribute("currentUser", u);
    	sServ.joinStudentToCourse(sc);
		return "redirect:/courses";
	}
	
	@GetMapping("/courses/{id}")
	public String viewCourse(Model model, @PathVariable("id") Long id, HttpSession session) {
		if(session.getAttribute("currentUser") == null) {
    		return "redirect:/";
    	} else {
    		Course c = sServ.findCourseById(id);
    		model.addAttribute("course", c);
    		Long userId = (Long) session.getAttribute("currentUser");
        	Student u = sServ.findStudentById(userId);
        	model.addAttribute("currentUser", u);
    		return "viewCourse.jsp";
    	}
	}
	
	
	@DeleteMapping("/remove/{userId}/{courseId}")
    public String removeStudentFromCourse(@PathVariable("courseId") Long courseId, @PathVariable("userId") Long userId) {
		sServ.deleteUserFromEvent(userId, courseId);
    	return "redirect:/courses";
	}
	
	@DeleteMapping("/delete/{id}")
	public String deleteCourse(@PathVariable("id") Long id) {
		sServ.deleteCourseById(id);
		return "redirect:/courses";
	}
	
	@GetMapping("/courses/{id}/edit")
	public String edit(@PathVariable("id") Long id, HttpSession session, Model model) {
    	if(session.getAttribute("currentUser") == null) {
    		return "redirect:/";
    	} else {
    		Long userId = (Long) session.getAttribute("currentUser");
    		Student u = sServ.findStudentById(userId);
        	model.addAttribute("currentUser", u);
        	Course c = sServ.findCourseById(id);
        	model.addAttribute("courseObj", c);
        	return "edit.jsp";
    	}
    }
	
	
	@PutMapping("/courses/{id}/edit")
    public String editCourse(Model model, @PathVariable("id") Long id, @Valid @ModelAttribute("courseObj") Course c, BindingResult result, HttpSession session) {
		if(result.hasErrors()) {
    		return "edit.jsp";
		}
		Long userId = (Long) session.getAttribute("currentUser");
    	Student u = sServ.findStudentById(userId);
    	model.addAttribute("currentUser", u);
    	sServ.saveCourse(c);
    	return "redirect:/courses";
	}	
}
