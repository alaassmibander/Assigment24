package com.example.assigment24.Service;

import com.example.assigment24.Exception.APIException;
import com.example.assigment24.Model.Course;
import com.example.assigment24.Model.Student;
import com.example.assigment24.Model.Teacher;
import com.example.assigment24.Repository.CourseRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CourseService {


    private CourseRepository courseRepository;
    private TeacherService teacherService;


    public List<Course> getAllCourses() {
        return courseRepository.findAll();
    }

    public Course getCourseBy(Integer courseId) {
        return courseRepository.findById(courseId).orElse(null);
    }

    public void addCourse(Course course) {
        courseRepository.save(course);
    }

    public void updateCourse(Integer courseId, Course course) {
        Course storedCourse = courseRepository.findById(courseId).orElse(null);

        if (storedCourse == null)
            throw new APIException("Course not found");

        storedCourse.setName(course.getName());
        courseRepository.save(storedCourse);
    }

    public void deleteCourse(Integer courseId) {
        Course course = courseRepository.findById(courseId).orElse(null);
        if (course == null)
            throw new APIException("Course not found");

        courseRepository.deleteById(courseId);
    }

    public void assignCourseToTeacher(Integer courseId, Integer teacherId) {
        Course course = courseRepository.findById(courseId).orElse(null);
        Teacher teacher = teacherService.getTeacherById(teacherId);

        if (course == null || teacher == null)
            throw new APIException("Course or Teacher were not found");

        course.setTeacher(teacher);
        courseRepository.save(course);
    }

    public String getTeacherNameForCourse(Integer courseId) {
        Course course = courseRepository.findById(courseId).orElse(null);
        if (course == null)
            throw new APIException("Course was not found");

        if (course.getTeacher() == null)
            throw new APIException("Teacher for course: " + courseId + " was not found");

        return course.getTeacher().getName();
    }

    public List<Student> getStudentList(Integer courseId) {
        Course course = courseRepository.findById(courseId).orElse(null);
        if (course == null)
            throw new APIException("Course was not found");

        return course.getStudents();
    }
}