package com.cdsg.bill.config;

import com.cdsg.bill.dao.UserDao;
import com.cdsg.bill.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.http.HttpServletResponse;
import java.util.Arrays;
import java.util.List;

@Configuration
public class WebSecurityConfig {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CustomJwtAuthenticationFilter customJwtAuthenticationFilter;

    @Bean
    public UserDetailsService userDetailsService() {
        return new UserDetailsService() {
            @Override
            public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
                UserDao userDao = userRepository.findByUsername(username);

                List<SimpleGrantedAuthority> roleList = Arrays.asList(new SimpleGrantedAuthority("ROLE_USER"));

                if (userDao != null) {
                    return new User(userDao.getUsername(), userDao.getPassword(), roleList);
                } else {
                    throw new UsernameNotFoundException("User " + username + " not found");
                }
            }
        };
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authConfig) throws Exception {
        return authConfig.getAuthenticationManager();
    }

    @Bean
    public SecurityFilterChain configure(HttpSecurity http) throws Exception {
        http.csrf().disable();

        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);

        http.authorizeRequests()
                .antMatchers("/login", "/createNewUser").permitAll()
                .anyRequest().authenticated();

        http.exceptionHandling().authenticationEntryPoint(
                (request, response, ex) -> {
                    response.sendError(
                            HttpServletResponse.SC_UNAUTHORIZED,
                            ex.getMessage()
                    );
                }
        );

        http.addFilterBefore(customJwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }

}