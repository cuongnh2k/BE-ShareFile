package vn.edu.cuongnh2k.berealtime.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import vn.edu.cuongnh2k.berealtime.dto.produces.RoleProduceDto;
import vn.edu.cuongnh2k.berealtime.entities.RoleEntity;

@Mapper(componentModel = "spring")
public abstract class RoleMapper implements CustomMapper {

    @Mapping(target = "users", ignore = true)
    public abstract RoleProduceDto toRoleProduceDto(RoleEntity roleEntity);
}
