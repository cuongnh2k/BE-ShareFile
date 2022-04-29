package vn.edu.cuongnh2k.berealtime.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import vn.edu.cuongnh2k.berealtime.dto.produces.DeviceProduceDto;
import vn.edu.cuongnh2k.berealtime.entities.DeviceEntity;

@Mapper(componentModel = "spring")
public abstract class DeviceMapper implements CustomMapper {

    @Mapping(target = "user", ignore = true)
    public abstract DeviceProduceDto toDeviceProduceDto(DeviceEntity deviceEntity);
}
