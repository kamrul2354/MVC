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


package ca.sheridan.hasankam.dao;

import ca.sheridan.hasankam.model.Course;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class CoursesDatabaseAccess {
    @Autowired
    protected NamedParameterJdbcTemplate jdbc;

    public long addCourse(Course course) {
        MapSqlParameterSource namedParameters = new MapSqlParameterSource();
        String insert = "INSERT INTO course (courseNumber, courseTitle, courseCredit) VALUES (:courseNumber, :courseTitle, :courseCredit)";
        namedParameters.addValue("courseNumber", course.getCourseNumber());
        namedParameters.addValue("courseTitle", course.getCourseTitle());
        namedParameters.addValue("courseCredit", course.getCourseCredit());
        return jdbc.update(insert, namedParameters);
    }

    public List<Course> selectCourses() {
        String query = "SELECT * FROM course";
        return jdbc.query(query, new MapSqlParameterSource(), new BeanPropertyRowMapper<>(Course.class));
    }
}
