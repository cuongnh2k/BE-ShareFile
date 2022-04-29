package vn.edu.cuongnh2k.be_realtime.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import vn.edu.cuongnh2k.be_realtime.dto.produces.RoleProduceDto;
import vn.edu.cuongnh2k.be_realtime.entities.RoleEntity;

@Mapper(componentModel = "spring")
public abstract class RoleMapper implements CustomMapper {

    @Mapping(target = "users", ignore = true)
    public abstract RoleProduceDto toRoleProduceDto(RoleEntity roleEntity);
}
