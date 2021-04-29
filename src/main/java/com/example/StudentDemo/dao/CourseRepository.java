package com.example.StudentDemo.dao;

import com.example.StudentDemo.model.Course;
import com.example.StudentDemo.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public interface CourseRepository  extends JpaRepository<Course, Integer> {

}
