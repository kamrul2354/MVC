package ca.sheridan.hasankam.dao;

import javax.sql.DataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

@Configuration
public class SpringJdbcConfig {
    @Bean
    public DataSource mysqlDataSource() { 
        DriverManagerDataSource dataSource = new DriverManagerDataSource(); 
        dataSource.setDriverClassName("com.mysql.cj.jdbc.Driver"); 
        dataSource.setUrl("jdbc:mysql://localhost:3306/coursesdb"); 
        dataSource.setUsername("root");
        dataSource.setPassword("Sheridan01717");
        return dataSource;
    }
}
