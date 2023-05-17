package com.teluguskillhub.springsecurity.Converter;

import com.teluguskillhub.springsecurity.Entities.User;
import com.teluguskillhub.springsecurity.Payload.Request.UserRequest;

public class UserConvertor {
    public static User userDtoToEntity(UserRequest userRequest){
        return User.builder()
                .name(userRequest.getName())
                .email(userRequest.getEmail())
                .password(userRequest.getPassword())
                .build();
    }
    public static UserRequest userEntityToDto(User user){
        return UserRequest.builder()
                .name(user.getName())
                .email(user.getEmail())
                .password(user.getPassword())
                .build();
    }
}
