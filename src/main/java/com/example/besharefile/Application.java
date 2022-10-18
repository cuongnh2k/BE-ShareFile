package com.example.besharefile;

import com.example.besharefile.entities.RolesEntity;
import com.example.besharefile.entities.UsersEntity;
import com.example.besharefile.enums.RoleEnum;
import com.example.besharefile.services.RoleService;
import com.example.besharefile.services.UserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class Application extends SpringBootServletInitializer {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(Application.class);
    }

    @Bean
    CommandLineRunner run(RoleService mRoleService, UserService mUserService) {
        return args -> {
            try {
                mRoleService.createOne(RolesEntity.builder().name(RoleEnum.ROLE_ADMIN).build());
            } catch (Exception ignored) {
            }
            try {
                mRoleService.createOne(RolesEntity.builder().name(RoleEnum.ROLE_USER).build());
            } catch (Exception ignored) {
            }
            try {
                mUserService.createOneAdmin(UsersEntity.builder()
                        .email("cuongnh2k@gmail.com")
                        .password("123")
                        .build());
            } catch (Exception ignored) {
            }
        };
    }
}
