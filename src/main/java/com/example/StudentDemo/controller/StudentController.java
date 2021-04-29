package com.example.StudentDemo.controller;

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

    @GetMapping(value="/students", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List> getStudents() {
        System.out.println("0");
        List students = studentService.getStudents();
        if(!students.isEmpty()){
            return new ResponseEntity<>( students, HttpStatus.OK);
        }
        return new ResponseEntity<>( null, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @GetMapping(value="/students/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Student> getStudent(@PathVariable("id") int id) {
        System.out.println("2");
     Student student   =   studentService.getStudentById(id);
        if(student!=null){
            return new ResponseEntity<>( student, HttpStatus.OK);
        }
        return new ResponseEntity<>( null, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @PostMapping(value="/students", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Student> createStudent(@RequestBody Student student) {
        return new ResponseEntity<>(studentService.saveStudent(student), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @PutMapping(value="/students/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Student> updateStudent(@PathVariable("id") int id,@RequestBody Student student) {
         Student studentUpdate = studentService.updateStudent(id,student);
        return new ResponseEntity<>(studentUpdate, HttpStatus.OK);
    }

    @DeleteMapping(value="/students/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<HttpStatus> deleteStudent(@PathVariable("id") int id) {
          studentService.deleteStudentById(id);
        return new ResponseEntity<>( HttpStatus.NO_CONTENT);
    }

    @PatchMapping(value="/students/add/{id}/{courseId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Student> assignCourseToStudent(@PathVariable("id") int id,@PathVariable("courseId") int courseId) throws Exception {
        Student studentUpdate = studentService.assignCourse(id,courseId);
        return new ResponseEntity<>(studentUpdate, HttpStatus.OK);
    }

    @PatchMapping(value="/students/remove/{id}/{courseId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Student> removeCourseFromStudent(@PathVariable("id") int id,@PathVariable("courseId") int courseId) throws Exception {
        Student studentUpdate = studentService.removeCourse(id,courseId);
        return new ResponseEntity<>(studentUpdate, HttpStatus.OK);
    }
}
