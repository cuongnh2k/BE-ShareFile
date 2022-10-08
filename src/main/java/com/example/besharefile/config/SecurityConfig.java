package com.example.besharefile.config;

import com.example.besharefile.enums.RoleEnum;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import static org.springframework.security.config.http.SessionCreationPolicy.STATELESS;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final UserDetailServiceConfig mUserDetailServiceConfig;
    private final FilterConfig mFilterConfig;

    @Value("${base_api}")
    private String BASE_API;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(mUserDetailServiceConfig).passwordEncoder(passwordEncoder());
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.cors();
        http.csrf().disable();
        http.sessionManagement().sessionCreationPolicy(STATELESS);
        http.authorizeRequests().antMatchers(BASE_API + "/basic/**").permitAll();
        http.authorizeRequests().antMatchers(BASE_API + "/user/**")
                .hasAnyAuthority(RoleEnum.ROLE_USER.name(), RoleEnum.ROLE_ADMIN.name());
        http.authorizeRequests().antMatchers(BASE_API + "/admin/**")
                .hasAnyAuthority(RoleEnum.ROLE_ADMIN.name());
        http.authorizeRequests().anyRequest().authenticated();
        http.addFilterBefore(mFilterConfig, UsernamePasswordAuthenticationFilter.class);
    }
}