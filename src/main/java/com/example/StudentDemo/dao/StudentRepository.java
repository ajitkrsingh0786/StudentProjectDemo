package com.example.StudentDemo.dao;

import com.example.StudentDemo.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public interface StudentRepository extends JpaRepository<Student, Integer> {

//    @Query(value = "delete from students_courses c where c.course_id=?1")
//    void removeStudentsByCourseId(int courseId);
}
