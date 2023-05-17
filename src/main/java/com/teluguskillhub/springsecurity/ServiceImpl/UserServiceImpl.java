package com.teluguskillhub.springsecurity.ServiceImpl;

import com.teluguskillhub.springsecurity.Entities.User;
import com.teluguskillhub.springsecurity.Payload.Request.UserRequest;
import com.teluguskillhub.springsecurity.Repository.UserRepository;
import com.teluguskillhub.springsecurity.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import static com.teluguskillhub.springsecurity.Converter.UserConvertor.userDtoToEntity;
import static com.teluguskillhub.springsecurity.Converter.UserConvertor.userEntityToDto;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public UserRequest createUser(UserRequest userRequest) {
        User user = userDtoToEntity(userRequest); //We are using static import and not even autowired/imported UserConverter class
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        User savedUser = userRepository.save(user);
        return userEntityToDto(savedUser);
    }
}
