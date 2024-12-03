package com.template.backendtemplate.core.auth.mapper;

import com.template.backendtemplate.core.auth.entity.UserEntity;
import com.template.backendtemplate.core.auth.model.UserRegisterDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {
    UserEntity toEntity(UserRegisterDto userRegisterDto);
}