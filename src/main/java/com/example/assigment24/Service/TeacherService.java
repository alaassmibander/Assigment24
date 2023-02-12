package com.example.assigment24.Service;

import com.example.assigment24.Exception.APIException;
import com.example.assigment24.Model.Teacher;
import com.example.assigment24.Repository.TeacherRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TeacherService {


    private final TeacherRepository teacherRepository;


    public List<Teacher> getAllTeachers() {
        return teacherRepository.findAll();
    }

    public Teacher getTeacherById(Integer teacherId) {
        Teacher teacher = teacherRepository.findById(teacherId).orElse(null);
        if (teacher == null)
            throw new APIException("Teacher was not found");
        return teacher;
    }

    public void addTeacher(Teacher teacher) {
        teacherRepository.save(teacher);
    }

    public void updateTeacher(Integer teacherId, Teacher teacher) {
        Teacher storedTeacher = getTeacherById(teacherId);

        storedTeacher.setAge(teacher.getAge());
        storedTeacher.setName(teacher.getName());
        storedTeacher.setEmail(teacher.getEmail());
        storedTeacher.setSalary(teacher.getSalary());
        storedTeacher.setAddress(teacher.getAddress());
        teacherRepository.save(storedTeacher);
    }

    public void deleteTeacher(Integer teacherId) {
        getTeacherById(teacherId);
        teacherRepository.deleteById(teacherId);
    }


}