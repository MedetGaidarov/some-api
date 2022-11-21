package com.example.backendjavaapijob.ui.dto.mapper;


import com.example.backendjavaapijob.domain.user.model.User;
import com.example.backendjavaapijob.ui.dto.user.UserDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = "spring")
public interface UserMapper {
    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);


    @Mapping(target = "email", source = "userDto.email")
    @Mapping(target = "username", source = "userDto.username")
    @Mapping(target="password", source = "userDto.password")
    User toUser(UserDto userDto);
    @Mapping(target = "email", source = "user.email")
    @Mapping(target = "username", source = "user.username")
    @Mapping(target="password", source = "user.password")

    UserDto toUserDto(User user);

}
