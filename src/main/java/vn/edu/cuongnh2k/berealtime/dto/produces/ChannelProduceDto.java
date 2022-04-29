package vn.edu.cuongnh2k.berealtime.dto.produces;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import vn.edu.cuongnh2k.berealtime.basess.BaseProduceDto;
import vn.edu.cuongnh2k.berealtime.enums.ChannelEnum;

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