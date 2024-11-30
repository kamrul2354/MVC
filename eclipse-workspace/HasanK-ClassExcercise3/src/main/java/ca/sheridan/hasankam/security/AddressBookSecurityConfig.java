package ca.sheridan.hasankam.security;
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
public class AddressBookSecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
            .authorizeHttpRequests(auth -> auth
                .requestMatchers("/index").permitAll()
                .requestMatchers("/AddContact/**", "/EditContact/**").hasAuthority("OWNER")
                .requestMatchers("/ListContacts/**").hasAuthority("GUEST")
                .anyRequest().authenticated()
            )
            .exceptionHandling().accessDeniedPage("/accessDenied")
            .and()
            .formLogin()
            .and()
            .httpBasic();
        return http.build();
    }

    @Bean
    public UserDetailsService users() {
        UserBuilder builder = User.withDefaultPasswordEncoder();
        UserDetails owner = builder.username("owner").password("owner123").authorities("OWNER").build();
        UserDetails guest = builder.username("guest").password("guest123").authorities("GUEST").build();
        return new InMemoryUserDetailsManager(owner, guest);
    }
}
