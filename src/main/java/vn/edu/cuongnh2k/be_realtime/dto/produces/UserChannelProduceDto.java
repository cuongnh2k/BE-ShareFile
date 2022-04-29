package vn.edu.cuongnh2k.be_realtime.dto.produces;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import vn.edu.cuongnh2k.be_realtime.basess.BaseProduceDto;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
public class UserChannelProduceDto extends BaseProduceDto<Long> {

    private String name;

    private UserProduceDto user;

    private ChannelProduceDto channel;
}