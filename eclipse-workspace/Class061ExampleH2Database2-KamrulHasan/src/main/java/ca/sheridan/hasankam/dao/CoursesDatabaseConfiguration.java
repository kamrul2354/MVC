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

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import javax.sql.DataSource;

@Configuration
public class CoursesDatabaseConfiguration {
    @Bean
    public NamedParameterJdbcTemplate namedParameterJdbcTemplate(DataSource dataSource) {
        return new NamedParameterJdbcTemplate(dataSource);
    }
}
