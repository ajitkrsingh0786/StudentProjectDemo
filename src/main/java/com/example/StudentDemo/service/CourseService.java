package com.example.StudentDemo.service;

import com.example.StudentDemo.dao.CourseRepository;
import com.example.StudentDemo.model.Course;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CourseService {
    @Autowired
    CourseRepository courseRepository;

    public List getCourses() {
        return courseRepository.findAll();
    }

    public Course getCourseById(int id) {
        Optional<Course> courseData = courseRepository.findById(id);
        return courseData.get();
    }

    public Course saveCourse(Course course) {
        return courseRepository.save(course);
    }

    public Course updateCourse(int id, Course course) {
        Optional<Course> courseData = courseRepository.findById(id);
        if (courseData.isPresent()) {
            Course newCourse = courseData.get();
            newCourse.setCourseName(course.getCourseName());
            newCourse.setDuration(course.getDuration());
            return courseRepository.save(newCourse);
        }
        return null;
    }

    public void deleteCourseById(int id) {

        courseRepository.deleteById(id);
    }
}
