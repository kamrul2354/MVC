package ca.sheridan.hasankam.controller;

import ca.sheridan.hasankam.dao.CoursesDatabaseAccess;
import ca.sheridan.hasankam.model.Course;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class CourseController {

    @Autowired
    private CoursesDatabaseAccess cda;

    // Entry point - Accessible by all roles
    @GetMapping("/index")
    public String index() {
        return "index";
    }
    // Mapping for Access Denied
    @GetMapping("/accessDenied")
    public String accessDenied() {
        return "accessDenied";
    }
    

    // Add Course - Accessible only by FACULTY
    @GetMapping("/AddCourse/courseDataInput")
    public String courseDataInput(Model model) {
        model.addAttribute("course", new Course());
        return "AddCourse/courseDataInput";
    }

    @PostMapping("/AddCourse/courseDataInput")
    public String addCourse(Model model, @ModelAttribute Course course) {
        String message;
        long numberOfRows = cda.addCourse(course);
        if (numberOfRows > 0) {
            message = "Success! The course object successfully added to the database.";
        } else {
            message = "Failure --- The course object is not added to the database.";
        }
        model.addAttribute("message", message);
        return "AddCourse/courseAddConfirm";
    }

    // List Courses - Accessible only by STUDENT
    @GetMapping("/ListOfCourses/listOfCourses")
    public String viewListOfCourses(Model model) {
        List<Course> courses = cda.selectCourses();
        model.addAttribute("courses", courses);
        return "ListOfCourses/listOfCourses";
    }

    // Edit Course - Accessible only by ADMIN
    @GetMapping("/EditCourse/editableListOfCourses")
    public String editableListOfCourses(Model model) {
        List<Course> courses = cda.selectCourses();
        model.addAttribute("courses", courses);
        return "EditCourse/editableListOfCourses";
    }

    @GetMapping("/EditCourse/editCourse/{id}")
    public String editCourse(@PathVariable("id") Integer id, Model model) {
        Course course = cda.selectCourseById(id);
        if (course == null) {
            model.addAttribute("error", "Course with ID " + id + " not found.");
            return "error";
        }
        model.addAttribute("course", course);
        return "EditCourse/editCourseData";
    }

    @PostMapping("/EditCourse/updateCourse")
    public String updateCourse(Model model, @ModelAttribute Course course) {
        String message;
        long rowsUpdated = cda.updateCourseById(course.getId(), course);
        if (rowsUpdated > 0) {
            message = "Success! The course object successfully updated in the database.";
        } else {
            message = "Failure --- The course object is not updated in the database.";
        }
        model.addAttribute("message", message);
        return "EditCourse/editOutcome";
    }
}
