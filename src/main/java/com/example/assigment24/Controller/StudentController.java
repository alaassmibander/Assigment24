package com.example.assigment24.Controller;

import com.example.assigment24.Model.Student;
import com.example.assigment24.Service.StudentService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/student")
public class StudentController {

    private final StudentService studentService;


    @GetMapping("/get")
    public ResponseEntity getStudent() {
        return ResponseEntity.status(200).body(studentService.getAllStudents());
    }

    @PostMapping("/add")
    public ResponseEntity addStudent(@RequestBody Student student) {
        studentService.addStudent(student);
        return ResponseEntity.status(200).body("Student was added.");
    }

    @PutMapping("/update/{studentId}")
    public ResponseEntity updateStudent(@RequestBody @Valid Student student, @PathVariable Integer studentId) {
        studentService.updateStudent(studentId, student);
        return ResponseEntity.status(200).body("Student updates");
    }

    @DeleteMapping("/delete/{studentId}")
    public ResponseEntity deleteStudent(@PathVariable Integer studentId) {
        studentService.deleteStudent(studentId);
        return ResponseEntity.status(200).body("Student deleted");
    }

    @PutMapping("/assign/{studentId}/course/{courseId}")
    public ResponseEntity assignCourseToStudent(@PathVariable Integer studentId, @PathVariable Integer courseId) {
        studentService.assignStudentToCourse(studentId, courseId);
        return ResponseEntity.status(200).body("Course with id: " + courseId + ", was assigned to student: " + studentId);
    }

    @PutMapping("/unassign/{studentId}/course/{courseId}")
    public ResponseEntity unassignCourseToStudent(@PathVariable Integer studentId, @PathVariable Integer courseId) {
        studentService.unAssignStudentFromCourse(studentId, courseId);
        return ResponseEntity.status(200).body("Course with id: " + courseId + ", was unassigned from student: " + studentId);
    }

    @PutMapping("/{studentId}/major")
    public ResponseEntity changeStudentMajor(@RequestBody Student student, @PathVariable Integer studentId) {
        studentService.changeStudentMajor(studentId, student);
        return ResponseEntity.status(200).body("student with id: " + studentId + " major was changed");
    }

}