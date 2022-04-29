package vn.edu.cuongnh2k.berealtime.dto.produces;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import vn.edu.cuongnh2k.berealtime.basess.BaseProduceDto;
import vn.edu.cuongnh2k.berealtime.enums.MessageEnum;

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