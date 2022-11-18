package com.example.backendjavaapijob.ui.dto.mapper;


import com.example.backendjavaapijob.domain.user.model.User;
import com.example.backendjavaapijob.ui.dto.user.UserDto;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = "spring")
public interface UserMapper {
    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);
    User toUser(UserDto userDto);
    UserDto toUserDto(User user);

}
