package vn.edu.cuongnh2k.be_realtime.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import vn.edu.cuongnh2k.be_realtime.dto.produces.DeviceProduceDto;
import vn.edu.cuongnh2k.be_realtime.entities.DeviceEntity;

@Mapper(componentModel = "spring")
public abstract class DeviceMapper implements CustomMapper {

    @Mapping(target = "user", ignore = true)
    public abstract DeviceProduceDto toDeviceProduceDto(DeviceEntity deviceEntity);
}
