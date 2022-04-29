package vn.edu.cuongnh2k.be_realtime.dto.produces;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import vn.edu.cuongnh2k.be_realtime.basess.BaseProduceDto;
import vn.edu.cuongnh2k.be_realtime.enums.ChannelEnum;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
public class ChannelProduceDto extends BaseProduceDto<Long> {

    private String name;

    private ChannelEnum type;

    private List<UserChannelProduceDto> userChannels;

    private List<MessageProduceDto> messages;
}