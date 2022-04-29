package vn.edu.cuongnh2k.be_realtime;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import vn.edu.cuongnh2k.be_realtime.entities.RoleEntity;
import vn.edu.cuongnh2k.be_realtime.entities.UserEntity;
import vn.edu.cuongnh2k.be_realtime.enums.RoleEnum;
import vn.edu.cuongnh2k.be_realtime.services.impl.RoleServiceImpl;
import vn.edu.cuongnh2k.be_realtime.services.impl.UserServiceImpl;

@SpringBootApplication
public class BeRealTimeApplication extends SpringBootServletInitializer {

    public static void main(String[] args) {
        SpringApplication.run(BeRealTimeApplication.class, args);
    }

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(BeRealTimeApplication.class);
    }

    @Bean
    CommandLineRunner run(RoleServiceImpl mRoleService, UserServiceImpl mUserService) {
        return args -> {
            try {
                mRoleService.create(RoleEntity.builder().name(RoleEnum.ROLE_ADMIN).build());
            } catch (Exception ignored) {
            }
            try {
                mRoleService.create(RoleEntity.builder().name(RoleEnum.ROLE_USER).build());
            } catch (Exception ignored) {
            }
            try {
                mUserService.createAdmin(UserEntity.builder()
                        .email("cuongnh2k@gmail.com")
                        .password("12345678")
                        .firstName("Cường")
                        .lastName("Nguyễn Hữu")
                        .build());
            } catch (Exception ignored) {
            }
            try {
                mUserService.createAdmin(UserEntity.builder()
                        .email("ct030408@actvn.edu.vn")
                        .password("12345678")
                        .firstName("Cường")
                        .lastName("Nguyễn Hữu")
                        .build());
            } catch (Exception ignored) {
            }
        };
    }
}
