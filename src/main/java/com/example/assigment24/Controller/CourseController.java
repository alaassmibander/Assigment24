package com.example.assigment24.Controller;

import com.example.assigment24.Model.Course;
import com.example.assigment24.Service.CourseService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/course")
public class CourseController {

    private final CourseService courseService;


    @GetMapping("/get")
    public ResponseEntity getCourse() {
        return ResponseEntity.status(200).body(courseService.getAllCourses());
    }

    @GetMapping("/{courseId}/teacherName")
    public ResponseEntity getTeacherNameForCourse(@PathVariable Integer courseId) {
        return ResponseEntity.status(200).body(courseService.getTeacherNameForCourse(courseId));
    }

    @GetMapping("/{courseId}/students")
    public ResponseEntity getCourseStudents(@PathVariable Integer courseId) {
        return ResponseEntity.status(200).body(courseService.getStudentList(courseId));
    }


    @PostMapping("/add")
    public ResponseEntity addCourse(@RequestBody Course course) {
        courseService.addCourse(course);
        return ResponseEntity.status(200).body("Course was added.");
    }

    @PutMapping("/update/{courseId}")
    public ResponseEntity updateCourse(@RequestBody @Valid Course course, @PathVariable Integer courseId) {
        courseService.updateCourse(courseId, course);
        return ResponseEntity.status(200).body("Course updates");
    }

    @DeleteMapping("/delete/{courseId}")
    public ResponseEntity deleteCourse(@PathVariable Integer courseId) {
        courseService.deleteCourse(courseId);
        return ResponseEntity.status(200).body("Course deleted");
    }

    @PutMapping("/assign/{courseId}/teacher/{teacherId}")
    public ResponseEntity assignTeacherToCourse(@PathVariable Integer courseId, @PathVariable Integer teacherId) {
        courseService.assignCourseToTeacher(courseId, teacherId);
        return ResponseEntity.status(200).body("Teacher with id: " + teacherId + ", was assigned to course: " + courseId);
    }


}