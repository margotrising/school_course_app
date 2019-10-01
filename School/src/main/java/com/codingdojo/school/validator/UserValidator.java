package com.codingdojo.school.validator;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.codingdojo.school.models.Student;



@Component
public class UserValidator implements Validator{
	@Override
    public boolean supports(Class<?> clazz) {
        return Student.class.equals(clazz);
    }
    
    // 2
    @Override
    public void validate(Object target, Errors errors) {
        Student student = (Student) target;
        
        if (!student.getPasswordConfirmation().equals(student.getPassword())) {
            // 3
            errors.rejectValue("passwordConfirmation", "Match");
        }         
    }
}
