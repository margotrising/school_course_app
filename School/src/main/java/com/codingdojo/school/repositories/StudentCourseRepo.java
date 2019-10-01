package com.codingdojo.school.repositories;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.codingdojo.school.models.Course;
import com.codingdojo.school.models.Student;
import com.codingdojo.school.models.StudentCourse;

@Repository
public interface StudentCourseRepo extends CrudRepository<StudentCourse, Long>{

	@Query("SELECT a FROM StudentCourse a WHERE student_id = ?1 AND course_id=?2")
	StudentCourse getStudentCourse(Long userId, Long courseId);

	@Modifying
	@Query("DELETE StudentCourse sc WHERE sc.student=?1 AND sc.course = ?2")
	int deleteAttendee(Student u, Course e);
}
