package vn.edu.cuongnh2k.be_realtime.dto.consumes;

import lombok.*;
import vn.edu.cuongnh2k.be_realtime.entities.UserEntity;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import java.io.Serializable;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserConsumeDto implements Serializable {

    @Email(message = "wrong email format",
            regexp = "^\\w{1,}([\\.-]{0,1}\\w{1,}){0,}@\\w{1,}([\\.-]{0,1}\\w{1,}){0,}(\\.\\w{2,3}){1,}$")
    private String email;

    @NotBlank(message = "password cannot be blank")
    private String password;

    @NotBlank(message = "firstName can not be blank")
    private String firstName;

    @NotBlank(message = "lastName can not be blank")
    private String lastName;

    public UserEntity toUserEntity() {
        return UserEntity.builder()
                .email(email)
                .password(password)
                .firstName(firstName)
                .lastName(lastName)
                .build();
    }
}
