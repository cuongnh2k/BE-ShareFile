package vn.edu.cuongnh2k.be_realtime.config;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;
import vn.edu.cuongnh2k.be_realtime.enums.RoleEnum;
import vn.edu.cuongnh2k.be_realtime.socket.UserHandshakeHandler;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.springframework.security.config.http.SessionCreationPolicy.STATELESS;

@Configuration
@EnableWebSecurity
@EnableJpaAuditing
@EnableWebSocketMessageBroker
@RequiredArgsConstructor
public class SecurityConfig extends WebSecurityConfigurerAdapter implements AuditorAware<String>, WebSocketMessageBrokerConfigurer {

    private final UserDetailServiceConfig mUserDetailServiceConfig;
    private final FilterConfig mFilterConfig;
    private final UserHandshakeHandler mUserHandshakeHandler;
    @Value("${base_api}")
    private String BASE_API;

    @Bean
    CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration config = new CorsConfiguration();
        config.setAllowCredentials(true);
        List<String> list = new ArrayList<>();
        list.add("http://localhost:8080");
        config.setAllowedOrigins(list);
        config.addAllowedHeader("*");
        config.addAllowedMethod("*");
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", config);
        return source;
    }

    @Override
    public void configureMessageBroker(final MessageBrokerRegistry registry) {
        registry.enableSimpleBroker("/topic");
        registry.setApplicationDestinationPrefixes("/ws");
    }

    @Override
    public void registerStompEndpoints(final StompEndpointRegistry registry) {
        registry.addEndpoint("/our-websocket")
                .setHandshakeHandler(mUserHandshakeHandler)
                .withSockJS();
    }

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
        http.authorizeRequests().antMatchers(
                BASE_API + "/basic/**").permitAll();
        http.authorizeRequests().antMatchers(
                BASE_API + "/user/**",
                BASE_API + "/device/**",
                BASE_API + "/message/**",
                BASE_API + "/user-channel/**",
                BASE_API + "/channel/**",
                "/our-websocket").hasAnyAuthority(RoleEnum.ROLE_USER.toString(), RoleEnum.ROLE_ADMIN.toString());
        http.authorizeRequests().antMatchers(BASE_API + "/admin/**").hasAnyAuthority(RoleEnum.ROLE_ADMIN.toString());
        http.authorizeRequests().anyRequest().authenticated();
        http.addFilterBefore(mFilterConfig, UsernamePasswordAuthenticationFilter.class);
    }
}