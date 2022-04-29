package vn.edu.cuongnh2k.be_realtime.dto.produces;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import vn.edu.cuongnh2k.be_realtime.basess.BaseProduceDto;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
public class UserProduceDto extends BaseProduceDto<Long> {

    private String email;

    private String firstName;

    private String lastName;

    private String avatar;

    private List<RoleProduceDto> roles;

    private List<DeviceProduceDto> devices;

    private List<UserChannelProduceDto> userChannels;

    private List<MessageProduceDto> messages;
}