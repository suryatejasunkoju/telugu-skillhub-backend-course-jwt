package com.teluguskillhub.springsecurity.Service;


import com.teluguskillhub.springsecurity.Payload.Request.TaskRequest;
import com.teluguskillhub.springsecurity.Payload.Request.UserRequest;

import java.util.List;

public interface UserService {
    public UserRequest createUser(UserRequest userRequest);
}
