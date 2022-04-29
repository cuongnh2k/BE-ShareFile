package vn.edu.cuongnh2k.be_realtime.dto.produces;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import vn.edu.cuongnh2k.be_realtime.basess.BaseProduceDto;
import vn.edu.cuongnh2k.be_realtime.enums.MessageEnum;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
public class MessageProduceDto extends BaseProduceDto<Long> {

    private String content;

    private MessageEnum type;

    private UserProduceDto user;

    private ChannelProduceDto channel;
}