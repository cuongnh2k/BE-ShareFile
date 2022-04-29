package vn.edu.cuongnh2k.be_realtime.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import vn.edu.cuongnh2k.be_realtime.dto.produces.MessageProduceDto;
import vn.edu.cuongnh2k.be_realtime.entities.MessageEntity;

@Mapper(componentModel = "spring")
public abstract class MessageMapper implements CustomMapper {

    @Mapping(target = "user", ignore = true)
    @Mapping(target = "channel", ignore = true)
    public abstract MessageProduceDto toMessageProduceDto(MessageEntity messageEntity);
}
