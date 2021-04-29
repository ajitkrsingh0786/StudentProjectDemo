package com.example.StudentDemo.controller;


import com.example.StudentDemo.model.Course;
import com.example.StudentDemo.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class CourseController {
    @Autowired
    CourseService courseService;

    @GetMapping(value="/courses", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List> getCourses() {

        List courses = courseService.getCourses();
        if(!courses.isEmpty()){
            return new ResponseEntity<>( courses, HttpStatus.OK);
        }
        return new ResponseEntity<>( null, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @GetMapping(value="/courses/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Course> getStudent(@PathVariable("id") int id) {
        Course course   =   courseService.getCourseById(id);
        if(course!=null){
            return new ResponseEntity<>( course, HttpStatus.OK);
        }
        return new ResponseEntity<>( null, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @PostMapping(value="/courses", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Course> createStudent(@RequestBody Course course) {
        return new ResponseEntity<>(courseService.saveCourse(course), HttpStatus.CREATED);
    }

    @PutMapping(value="/courses/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Course> updateStudent(@PathVariable("id") int id,@RequestBody Course course) {
        Course courseUpdate = courseService.updateCourse(id,course);
        return new ResponseEntity<>(courseUpdate, HttpStatus.OK);
    }

    @DeleteMapping(value="/courses/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<HttpStatus> deleteCourse(@PathVariable("id") int id) {
        courseService.deleteCourseById(id);
        return new ResponseEntity<>( HttpStatus.NO_CONTENT);
    }
}
