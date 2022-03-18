package com.onlinestudentmanagement.studentapplication.controller;

import com.onlinestudentmanagement.studentapplication.domain.Course;
import com.onlinestudentmanagement.studentapplication.domain.Student;
import com.onlinestudentmanagement.studentapplication.rep.ICourseRep;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class CourseController {
    private final ICourseRep iCourseRep;

    public CourseController(ICourseRep iCourseRep) {
        this.iCourseRep = iCourseRep;
    }
    //	List all course
    @RequestMapping("/courses")
    public String getCourses(Model model){
        model.addAttribute("courses", iCourseRep.findAll());
        return "course";
    }

    //	add course
    @GetMapping("/course/new")
    public String createCourseForm(Model model) {
        // created student object to hold student form data
        Course course = new Course();
        model.addAttribute("course", course);
        return "create_course";
    }

    //	add student
    @GetMapping("/students/new")
    public String createStudentForm(Model model) {

        // created student object to hold student form data
        Student student = new Student();
        model.addAttribute("student", student);

        return "create_student";
    }
    //	save course
    @PostMapping("/savecourse")
    public String saveCourse(@ModelAttribute("course") Course course) {
       iCourseRep.save(course);
        return "redirect:/course";
    }

    //	update student from page
    @GetMapping("/course/edit/{id}")
    public String editStudent(@PathVariable Long id, Model model) {
        Course existingStudent = iCourseRep.getById(id);
        Student students  = (Student)model.getAttribute("course");

        return "register";
    }
}
