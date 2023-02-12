package com.example.assigment24.Service;

import com.example.assigment24.Exception.APIException;
import com.example.assigment24.Model.Course;
import com.example.assigment24.Model.Student;
import com.example.assigment24.Repository.StudentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@RequiredArgsConstructor
public class StudentService {

    private final StudentRepository studentRepository;
    private final CourseService courseService;


    public List<Student> getAllStudents() {
        return studentRepository.findAll();
    }

    public void addStudent(Student student) {
        studentRepository.save(student);
    }

    public void updateStudent(Integer studentId, Student student) {
        Student storedStudent = studentRepository.findById(studentId).orElse(null);

        if (storedStudent == null)
            throw new APIException("Student not found");

        storedStudent.setName(student.getName());
        studentRepository.save(storedStudent);
    }

    public void deleteStudent(Integer studentId) {
        Student student = studentRepository.findById(studentId).orElse(null);
        if (student == null)
            throw new APIException("Student not found");

        studentRepository.deleteById(studentId);
    }

    public void assignStudentToCourse(Integer studentId, Integer courseId) {
        Student student = studentRepository.findById(studentId).orElse(null);
        Course course = courseService.getCourseBy(courseId);

        if (student == null || course == null)
            throw new APIException("Student or Course were not found");

        student.getCourses().add(course);
        course.getStudents().add(student);
        studentRepository.save(student);
        courseService.addCourse(course);
    }

    public void unAssignStudentFromCourse(Integer studentId, Integer courseId) {
        Student student = studentRepository.findById(studentId).orElse(null);
        Course course = courseService.getCourseBy(courseId);

        if (student == null || course == null)
            throw new APIException("Student or Course were not found");

        student.getCourses().remove(course);
        course.getStudents().remove(student);

        studentRepository.save(student);
        courseService.addCourse(course);
    }

    public void changeStudentMajor(Integer studentId, Student student) {
        Student storedStudent = studentRepository.findById(studentId).orElse(null);
        if (storedStudent == null)
            throw new APIException("Student was not found");


        for (Course course : storedStudent.getCourses()) {
            courseService.getStudentList(course.getId()).remove(storedStudent);
            courseService.addCourse(course);
        }
        storedStudent.setMajor(student.getMajor());

        studentRepository.save(storedStudent);
    }


}
