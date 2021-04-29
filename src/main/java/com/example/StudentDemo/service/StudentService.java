package com.example.StudentDemo.service;

import com.example.StudentDemo.dao.CourseRepository;
import com.example.StudentDemo.dao.StudentRepository;
import com.example.StudentDemo.exceptions.CustomException;
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

    public List getStudents() {
        return studentRepository.findAll();
    }

    public Student getStudentById(int id) throws CustomException {
        Optional<Student> studentData = studentRepository.findById(id);
        if (studentData.isEmpty()) {
            throw new CustomException("Student does not exit");
        }
        return studentData.get();
    }

    public Student saveStudent(Student student) throws CustomException {
        if (student.getRollNumber() == null || student.getName() == null || student.getBranch() == null) {
            throw new CustomException("Student's name, branch and rollRollNumber are required fields");
        }
        Optional<Student> studentData = studentRepository.findByRollNumber(student.getRollNumber());
        if (!studentData.isEmpty()) {
            throw new CustomException("Student with given roll number is already exist");
        }
        return studentRepository.save(student);
    }

    public Student updateStudent(int id, Student student) throws CustomException {
        Optional<Student> studentData = studentRepository.findById(id);

        if (studentData.isPresent()) {
            Student newStudent = studentData.get();
            if (student.getBranch() != null) {
                newStudent.setBranch(student.getBranch());
            }
            if (student.getName() != null) {
                newStudent.setName(student.getName());
            }
            if (student.getRollNumber() != null) {

                Optional<Student> studentData1 = studentRepository.findByRollNumber(student.getRollNumber());
                if (!studentData1.isEmpty() && studentData1.get().getId() != id) {
                    throw new CustomException("Student with given roll number is already exist");
                }
                newStudent.setRollNumber(student.getRollNumber());
            }
            return saveStudent(newStudent);
        }
        return null;
    }

    public void deleteStudentById(int id) throws CustomException {
        try {
            studentRepository.deleteById(id);
        }catch (Exception e){
            throw new CustomException("Student Can't Delete");
        }
    }

    public Student assignCourse(int id, int courseId) throws Exception {
        Optional<Student> studentData = studentRepository.findById(id);
        Optional<Course> courseData = courseRepository.findById(courseId);
        if (studentData.isEmpty()) {
            throw new CustomException("Student does not exit");
        }
        if (courseData.isEmpty()) {
            throw new CustomException("Course does not exit");
        }
        Student student = studentData.get();
        if (student.getCourses().contains(courseData.get())) {
            throw new CustomException("Student has already taken this course");
        } else {
            student.getCourses().add(courseData.get());
            return studentRepository.save(student);
        }
    }

    public Student removeCourse(int id, int courseId) throws Exception {
        Optional<Student> studentData = studentRepository.findById(id);
        Optional<Course> courseData = courseRepository.findById(courseId);
        if (studentData.isEmpty()) {
            throw new CustomException("Student does not exit");
        }
        if (courseData.isEmpty()) {
            throw new CustomException("Course does not exit");
        }
        Student student = studentData.get();
        if (!student.getCourses().contains(courseData.get())) {
            throw new CustomException("Student has not taken this course");
        } else {
            student.getCourses().remove(courseData.get());
            return studentRepository.save(student);
        }
    }
}
