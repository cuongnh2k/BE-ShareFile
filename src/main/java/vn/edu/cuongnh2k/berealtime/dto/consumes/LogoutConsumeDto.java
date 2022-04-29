package vn.edu.cuongnh2k.berealtime.dto.consumes;

import lombok.*;

import javax.validation.constraints.NotEmpty;
import java.io.Serializable;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class LogoutConsumeDto implements Serializable {

    @NotEmpty(message = "device not select")
    private Long[] deviceIds;
}
