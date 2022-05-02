package vn.edu.cuongnh2k.be_realtime.dto.consumes;

import lombok.*;
import vn.edu.cuongnh2k.be_realtime.enums.MessageEnum;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MessageConsumeDto implements Serializable {

    @NotBlank(message = "content cannot be blank")
    private String content;

    @NotBlank(message = "type cannot be blank")
    private MessageEnum type;
}
