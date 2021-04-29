package com.example.StudentDemo.controller;

import com.example.StudentDemo.exceptions.CustomException;
import com.example.StudentDemo.model.Student;
import com.example.StudentDemo.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class StudentController {

    @Autowired
    StudentService studentService;

    @GetMapping(value = "/students", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List> getStudents() {
        System.out.println("0");
        List students = studentService.getStudents();
        if (!students.isEmpty()) {
            return new ResponseEntity<>(students, HttpStatus.OK);
        }
        return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @GetMapping(value = "/students/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Student> getStudent(@PathVariable("id") int id) throws CustomException {
        System.out.println("2");
        Student student = studentService.getStudentById(id);
        if (student != null) {
            return new ResponseEntity<>(student, HttpStatus.OK);
        }
        return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @PostMapping(value = "/students", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Student> createStudent(@RequestBody Student student) throws CustomException {
        Student newStudent = studentService.saveStudent(student);
        if (newStudent != null) {
            return new ResponseEntity<>(newStudent, HttpStatus.OK);
        }
        return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @PutMapping(value = "/students/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Student> updateStudent(@PathVariable("id") int id, @RequestBody Student student) throws CustomException {
        Student studentUpdate = studentService.updateStudent(id, student);
        if (studentUpdate != null) {
            return new ResponseEntity<>(studentUpdate, HttpStatus.OK);
        }
        return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @DeleteMapping(value = "/students/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> deleteStudent(@PathVariable("id") int id) throws CustomException {
        studentService.deleteStudentById(id);
        return new ResponseEntity<>("Delete success",HttpStatus.NO_CONTENT);
    }

    @PatchMapping(value = "/students/add/{id}/{courseId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Student> assignCourseToStudent(@PathVariable("id") int id,
                                                         @PathVariable("courseId") int courseId) throws Exception {
        Student studentUpdate = studentService.assignCourse(id, courseId);
        return new ResponseEntity<>(studentUpdate, HttpStatus.OK);
    }

    @PatchMapping(value = "/students/remove/{id}/{courseId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Student> removeCourseFromStudent(@PathVariable("id") int id,
                                                           @PathVariable("courseId") int courseId) throws Exception {
        Student studentUpdate = studentService.removeCourse(id, courseId);
        return new ResponseEntity<>(studentUpdate, HttpStatus.OK);
    }
}
