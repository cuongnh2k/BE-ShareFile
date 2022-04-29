package vn.edu.cuongnh2k.be_realtime.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import vn.edu.cuongnh2k.be_realtime.dto.produces.UserChannelProduceDto;
import vn.edu.cuongnh2k.be_realtime.entities.UserChannelEntity;

@Mapper(componentModel = "spring")
public abstract class UserChannelMapper implements CustomMapper {

    @Mapping(target = "user", ignore = true)
    @Mapping(target = "channel", ignore = true)
    public abstract UserChannelProduceDto toUserChannelProduceDto(UserChannelEntity userChannelEntity);
}
