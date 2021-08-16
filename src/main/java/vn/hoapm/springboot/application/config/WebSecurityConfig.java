package vn.hoapm.springboot.application.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import vn.hoapm.springboot.application.config.jwt.JWTRequestFilter;
import vn.hoapm.springboot.application.config.jwt.JwtAuthenticationEntryPoint;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    private JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;
    @Qualifier("jwtUserDetailsService")
    @Autowired
    private UserDetailsService jwtUserDetailService;
    private PasswordEncoder passwordEncoder;
    private JWTRequestFilter jwtRequestFilter;

    @Autowired
    public void setPasswordEncoder(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    @Autowired
    public void setJwtAuthenticationEntryPoint(
            JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint) {
        this.jwtAuthenticationEntryPoint = jwtAuthenticationEntryPoint;
    }


    @Autowired
    public void setJwtRequestFilter(JWTRequestFilter jwtRequestFilter) {
        this.jwtRequestFilter = jwtRequestFilter;
    }


    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        // configure AuthenticationManager so that it knows from where to load
        // user for matching credentials
        // Use BCryptPasswordEncoder
        if (passwordEncoder == null) {
            // Password encoder, để Spring Security sử dụng mã hóa mật khẩu người dùng
            passwordEncoder = new BCryptPasswordEncoder();
        }
        auth.userDetailsService(jwtUserDetailService).passwordEncoder(passwordEncoder);
        //Cung cáp userservice, passwordEncorder cho spring security
    }

    @Override
    protected void configure(HttpSecurity httpSecurity) throws Exception {
        httpSecurity
                .csrf().disable()
                .addFilterAfter(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class)
                .authorizeRequests()
                .antMatchers("/register", "/login", "/logout").permitAll()
                .antMatchers("/users**").permitAll()
                .antMatchers("/users/**").permitAll()
                //.antMatchers("/users").access("hasAnyRole('ADMIN','USER')")
                .antMatchers(HttpMethod.DELETE,"/users/**").hasAnyAuthority("ADMIN","SUPERADMIN")
                .antMatchers("/upload").hasAnyAuthority("ADMIN","SUPERADMIN")
                .antMatchers(HttpMethod.POST, "/admin").hasAuthority("SUPERADMIN")
                .anyRequest().authenticated()
                .and()
                .exceptionHandling().authenticationEntryPoint(jwtAuthenticationEntryPoint)
                .and()
                .logout()
                .logoutUrl("/logout")
                .permitAll()
                .and()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS);
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        // Get AuthenticationManager bean
        return super.authenticationManagerBean();
    }


}
