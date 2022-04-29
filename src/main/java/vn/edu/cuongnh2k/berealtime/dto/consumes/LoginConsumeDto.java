package vn.edu.cuongnh2k.berealtime.dto.consumes;

import lombok.*;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import java.io.Serializable;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class LoginConsumeDto implements Serializable {

    @Email(message = "wrong email format",
            regexp = "^\\w{1,}([\\.-]{0,1}\\w{1,}){0,}@\\w{1,}([\\.-]{0,1}\\w{1,}){0,}(\\.\\w{2,3}){1,}$")
    private String email;

    @NotBlank(message = "password cannot be blank")
    private String password;
}
