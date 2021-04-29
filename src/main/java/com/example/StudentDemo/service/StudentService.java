package com.example.StudentDemo.service;

import com.example.StudentDemo.dao.CourseRepository;
import com.example.StudentDemo.dao.StudentRepository;
import com.example.StudentDemo.model.Course;
import com.example.StudentDemo.model.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StudentService {
    @Autowired
    StudentRepository studentRepository;
    @Autowired
    CourseRepository courseRepository;

    public List getStudents(){
        return studentRepository.findAll();
    }

    public Student getStudentById(int id){
        Optional<Student> studentData = studentRepository.findById(id);
        return  studentData.get();
    }

    public Student saveStudent(Student student) {
        return   studentRepository.save(student);
    }

    public Student updateStudent(int id, Student student){
        Optional<Student> studentData = studentRepository.findById(id);
        if(studentData.isPresent()){
            Student newStudent = studentData.get();
            newStudent.setBranch(student.getBranch());
            newStudent.setName(student.getName());
            newStudent.setRollNumber(student.getRollNumber());
            return saveStudent(newStudent);
        }
        return null;
    }

    public void deleteStudentById(int id){
         studentRepository.deleteById(id);
    }

    public Student assignCourse(int id, int courseId) throws Exception {
        Optional<Student> studentData = studentRepository.findById(id);
        Optional<Course> courseData = courseRepository.findById(courseId);
        if(studentData.isEmpty()){
            throw new Exception("Student does not exit");
        }
        if(courseData.isEmpty()){
            throw new Exception("Course does not exit");
        }
        Student student = studentData.get();
         if(student.getCourses().contains(courseData.get())){
             throw new Exception("Student has already taken this course");
         }else{
             student.getCourses().add(courseData.get());
             return studentRepository.save(student);
         }
    }

    public Student removeCourse(int id, int courseId) throws Exception {
        Optional<Student> studentData = studentRepository.findById(id);
        Optional<Course> courseData = courseRepository.findById(courseId);
        if(studentData.isEmpty()){
            throw new Exception("Student does not exit");
        }
        if(courseData.isEmpty()){
            throw new Exception("Course does not exit");
        }
        Student student = studentData.get();
        if(!student.getCourses().contains(courseData.get())){
            throw new Exception("Student has not taken this course");
        }else{
            student.getCourses().remove(courseData.get());
            return studentRepository.save(student);
        }
    }
}
