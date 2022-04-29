package vn.edu.cuongnh2k.berealtime.config;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import vn.edu.cuongnh2k.berealtime.enums.RoleEnum;

import java.util.Optional;

import static org.springframework.security.config.http.SessionCreationPolicy.STATELESS;

@Configuration
@EnableWebSecurity
@EnableJpaAuditing
@RequiredArgsConstructor
public class SecurityConfig extends WebSecurityConfigurerAdapter implements AuditorAware<String> {

    private final UserDetailServiceConfig mUserDetailServiceConfig;
    private final FilterConfig mFilterConfig;

    @Value("${base_api}")
    private String BASE_API;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    public Optional<String> getCurrentAuditor() {
        try {
            return Optional.ofNullable(SecurityContextHolder.getContext().getAuthentication().getName());
        } catch (Exception ignored) {
            return Optional.of("anonymousUser");
        }
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
        http.authorizeRequests().antMatchers(BASE_API + "/user/**").hasAnyAuthority(RoleEnum.ROLE_USER.toString());
        http.authorizeRequests().antMatchers(BASE_API + "/device/**").hasAnyAuthority(RoleEnum.ROLE_USER.toString());
        http.authorizeRequests().antMatchers(BASE_API + "/message/**").hasAnyAuthority(RoleEnum.ROLE_USER.toString());
        http.authorizeRequests().antMatchers(BASE_API + "/user-channel/**").hasAnyAuthority(RoleEnum.ROLE_USER.toString());
        http.authorizeRequests().antMatchers(BASE_API + "/channel/**").hasAnyAuthority(RoleEnum.ROLE_USER.toString());
        http.authorizeRequests().antMatchers(BASE_API + "/admin/**").hasAnyAuthority(RoleEnum.ROLE_ADMIN.toString());
        http.authorizeRequests().anyRequest().authenticated();
        http.addFilterBefore(mFilterConfig, UsernamePasswordAuthenticationFilter.class);
    }
}