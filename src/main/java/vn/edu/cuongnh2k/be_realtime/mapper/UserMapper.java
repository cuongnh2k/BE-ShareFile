package vn.edu.cuongnh2k.be_realtime.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import vn.edu.cuongnh2k.be_realtime.dto.produces.UserProduceDto;
import vn.edu.cuongnh2k.be_realtime.entities.UserEntity;

@Mapper(componentModel = "spring")
public abstract class UserMapper implements CustomMapper {

    @Mapping(target = "roles", ignore = true)
    @Mapping(target = "devices", ignore = true)
    @Mapping(target = "userChannels", ignore = true)
    @Mapping(target = "messages", ignore = true)
    public abstract UserProduceDto toUserProduceDto(UserEntity userEntity);
}
