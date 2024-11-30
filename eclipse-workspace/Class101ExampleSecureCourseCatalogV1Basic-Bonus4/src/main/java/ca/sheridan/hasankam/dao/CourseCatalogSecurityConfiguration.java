package ca.sheridan.hasankam.dao;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.User.UserBuilder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class CourseCatalogSecurityConfiguration {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSec) throws Exception {
        httpSec
            .authorizeHttpRequests((authorizeHttpRequests) -> authorizeHttpRequests
                .requestMatchers("/index").permitAll() // Accessible by all users
                .requestMatchers("/AddCourse/**").hasAuthority("FACULTY") // Only FACULTY can access AddCourse pages
                .requestMatchers("/EditCourse/**").hasAuthority("ADMIN") // Only ADMIN can access EditCourse pages
                .requestMatchers("/ListOfCourses/**").hasAuthority("STUDENT") // Only STUDENT can access ListOfCourses pages
                .anyRequest().authenticated() // All other requests require authentication
            )
            .exceptionHandling()
                .accessDeniedPage("/accessDenied") // Custom access denied page
            .and()
            .httpBasic(); // Use HTTP Basic authentication
        return httpSec.build();
    }

    @Bean
    public UserDetailsService users() {
        // Deprecated password encoder for simplicity
        @SuppressWarnings("deprecation")
        UserBuilder users = User.withDefaultPasswordEncoder();

        UserDetails admin = users
            .username("userA")
            .password("aaaa")
            .authorities("ADMIN")
            .build();

        UserDetails faculty = users
            .username("userF")
            .password("ffff")
            .authorities("FACULTY")
            .build();

        UserDetails student = users
            .username("userS")
            .password("ssss")
            .authorities("STUDENT")
            .build();

        return new InMemoryUserDetailsManager(admin, faculty, student);
    }
}
