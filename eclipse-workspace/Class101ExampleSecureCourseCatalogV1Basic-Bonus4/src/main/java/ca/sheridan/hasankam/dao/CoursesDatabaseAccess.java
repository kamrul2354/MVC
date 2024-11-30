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

    // Method to add a new course
    public long addCourse(Course course) {
        MapSqlParameterSource namedParameters = new MapSqlParameterSource();
        String insert = "INSERT INTO course (courseNumber, courseTitle, courseCredit) VALUES (:courseNumber, :courseTitle, :courseCredit)";
        namedParameters.addValue("courseNumber", course.getCourseNumber());
        namedParameters.addValue("courseTitle", course.getCourseTitle());
        namedParameters.addValue("courseCredit", course.getCourseCredit());
        return jdbc.update(insert, namedParameters);
    }

    // Method to get the list of all courses
    public List<Course> selectCourses() {
        String query = "SELECT * FROM course";
        return jdbc.query(query, new MapSqlParameterSource(), new BeanPropertyRowMapper<>(Course.class));
    }

    // Method to get a specific course by ID
    public Course selectCourseById(int id) {
        MapSqlParameterSource namedParameters = new MapSqlParameterSource();
        String query = "SELECT * FROM course WHERE id = :id";
        namedParameters.addValue("id", id);
        List<Course> courses = jdbc.query(query, namedParameters, new BeanPropertyRowMapper<>(Course.class));
        return courses.isEmpty() ? null : courses.get(0);
    }

    // Method to update a course by ID
    public long updateCourseById(int courseId, Course course) {
        MapSqlParameterSource namedParameters = new MapSqlParameterSource();
        String update = "UPDATE course SET courseNumber = :courseNumber, courseTitle = :courseTitle, courseCredit = :courseCredit WHERE id = :id";
        namedParameters.addValue("id", courseId);
        namedParameters.addValue("courseNumber", course.getCourseNumber());
        namedParameters.addValue("courseTitle", course.getCourseTitle());
        namedParameters.addValue("courseCredit", course.getCourseCredit());
        return jdbc.update(update, namedParameters);
    }
}
