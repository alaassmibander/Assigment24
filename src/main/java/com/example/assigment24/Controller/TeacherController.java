package com.example.assigment24.Controller;

import com.example.assigment24.Model.Teacher;
import com.example.assigment24.Service.TeacherService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/teacher")
public class TeacherController {

    private final TeacherService teacherService;


    @GetMapping("/get")
    public ResponseEntity getTeachers() {
        return ResponseEntity.status(200).body(teacherService.getAllTeachers());
    }

    @GetMapping("/get/{teacherId}")
    public ResponseEntity getTeacher(@PathVariable Integer teacherId) {
        return ResponseEntity.status(200).body(teacherService.getTeacherById(teacherId));
    }

    @PostMapping("/add")
    public ResponseEntity addTeacher(@RequestBody Teacher teacher) {
        teacherService.addTeacher(teacher);
        return ResponseEntity.status(200).body("Teacher was added.");
    }

    @PutMapping("/update/{teacherId}")
    public ResponseEntity updateTeacher(@RequestBody @Valid Teacher teacher, @PathVariable Integer teacherId) {
        teacherService.updateTeacher(teacherId, teacher);
        return ResponseEntity.status(200).body("Teacher updates");
    }

    @DeleteMapping("/delete/{teacherId}")
    public ResponseEntity deleteTeacher(@PathVariable Integer teacherId) {
        teacherService.deleteTeacher(teacherId);
        return ResponseEntity.status(200).body("Teacher deleted");
    }
}
