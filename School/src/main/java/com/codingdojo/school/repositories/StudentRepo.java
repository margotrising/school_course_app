package com.codingdojo.school.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.codingdojo.school.models.Student;

@Repository
public interface StudentRepo extends CrudRepository<Student, Long>{
	Student findByEmail(String email);
}
