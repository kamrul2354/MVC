package ca.sheridan.hasankam.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class ProductCatalogSecurityConfiguration {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSec) throws Exception {
        httpSec
            .authorizeHttpRequests(authz -> authz
                .requestMatchers("/index", "/login", "/css/**").permitAll()
                .requestMatchers("/AddProduct/**", "/EditProduct/**", "/DeleteProduct/**").hasAuthority("ADMIN")
                .requestMatchers("/ListProducts/**").hasAuthority("SALES")
                .anyRequest().authenticated()
            )
            .formLogin(form -> form
                .loginPage("/login")
                .defaultSuccessUrl("/index", true)
                .failureUrl("/login?error=true")
                .permitAll()
            )
            .logout(logout -> logout
                .invalidateHttpSession(true)
                .clearAuthentication(true)
                .logoutSuccessUrl("/login?logout")
                .permitAll()
            )
            .exceptionHandling()
            .accessDeniedPage("/accessDenied")
            .and()
            .httpBasic();
        return httpSec.build();
    }

    @Bean
    public UserDetailsService users() {
        User.UserBuilder users = User.withDefaultPasswordEncoder();

        UserDetails admin = users.username("UserA").password("aaaa").authorities("ADMIN").build();
        UserDetails sales = users.username("UserB").password("bbbb").authorities("SALES").build();
        UserDetails both = users.username("UserC").password("cccc").authorities("ADMIN", "SALES").build();

        return new InMemoryUserDetailsManager(admin, sales, both);
    }
}