/*
 * Author: Kamrul Hasan
 * Student ID: 991769795
 * Date: [03-11-2024]
 * 
 * Description:
 * This program is developed as part of an assignment to create a Course Catalog 
 * Management System using Spring Boot, Thymeleaf, and an H2 in-memory database. 
 * The application enables users to input course details, view a confirmation of 
 * the added course, and list all courses in a tabular format. Following the 
 * Model-View-Controller (MVC) design pattern, this system allows for clean separation 
 * of concerns, where course information is stored in the H2 database and displayed 
 * dynamically on web pages. The application includes a form for course input, a list 
 * view for viewing all courses, and utilizes JDBC template methods for efficient 
 * database interaction.
 */



package ca.sheridan.hasankam.controller;

import ca.sheridan.hasankam.dao.CoursesDatabaseAccess;
import ca.sheridan.hasankam.model.Course;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class CourseController {
    @Autowired
    private CoursesDatabaseAccess cda;

    @GetMapping("/courseDataInput")
    public String courseDataInput(Model model) {
        model.addAttribute("course", new Course());
        return "courseDataInput";
    }

    @PostMapping("/courseDataInput")
    public String addCourse(Model model, @ModelAttribute Course course) {
        cda.addCourse(course);
        model.addAttribute("message", "Course added successfully!");
        return "courseAddConfirm";
    }

    @GetMapping("/listOfCourses")
    public String viewListOfCourses(Model model) {
        List<Course> courses = cda.selectCourses();
        model.addAttribute("courses", courses);
        return "listOfCourses";
    }

    @GetMapping("/index")
    public String index() {
        return "index";
    }
}
