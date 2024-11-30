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



package ca.sheridan.hasankam.model;

import java.io.Serializable;

public class Course implements Serializable {
    private int id; // primary key
    private String courseNumber;
    private String courseTitle;
    private int courseCredit;

    // no-arg constructor
    public Course() {}

    // Getters and Setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public String getCourseNumber() { return courseNumber; }
    public void setCourseNumber(String courseNumber) { this.courseNumber = courseNumber; }
    public String getCourseTitle() { return courseTitle; }
    public void setCourseTitle(String courseTitle) { this.courseTitle = courseTitle; }
    public int getCourseCredit() { return courseCredit; }
    public void setCourseCredit(int courseCredit) { this.courseCredit = courseCredit; }
}
