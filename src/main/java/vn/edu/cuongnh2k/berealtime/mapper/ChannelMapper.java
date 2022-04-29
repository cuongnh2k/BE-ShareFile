package vn.edu.cuongnh2k.berealtime.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import vn.edu.cuongnh2k.berealtime.dto.produces.ChannelProduceDto;
import vn.edu.cuongnh2k.berealtime.entities.ChannelEntity;

@Mapper(componentModel = "spring")
public abstract class ChannelMapper implements CustomMapper {

    @Mapping(target = "userChannels", ignore = true)
    @Mapping(target = "messages", ignore = true)
    public abstract ChannelProduceDto toChannelProduceDto(ChannelEntity channelEntity);
}
