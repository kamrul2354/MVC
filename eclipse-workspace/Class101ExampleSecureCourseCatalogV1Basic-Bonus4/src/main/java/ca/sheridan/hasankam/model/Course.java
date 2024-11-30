package ca.sheridan.hasankam.model;

import java.io.Serializable;

public class Course implements Serializable {
    private Integer id; // Changed from int to Integer
    private String courseNumber;
    private String courseTitle;
    private int courseCredit;

    // No-arg constructor
    public Course() {}

    // Getters and Setters
    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }
    public String getCourseNumber() { return courseNumber; }
    public void setCourseNumber(String courseNumber) { this.courseNumber = courseNumber; }
    public String getCourseTitle() { return courseTitle; }
    public void setCourseTitle(String courseTitle) { this.courseTitle = courseTitle; }
    public int getCourseCredit() { return courseCredit; }
    public void setCourseCredit(int courseCredit) { this.courseCredit = courseCredit; }

}
