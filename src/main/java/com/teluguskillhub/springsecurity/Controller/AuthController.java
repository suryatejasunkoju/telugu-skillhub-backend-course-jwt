package com.teluguskillhub.springsecurity.Controller;

import com.teluguskillhub.springsecurity.Payload.JwtAuthenticationResponse;
import com.teluguskillhub.springsecurity.Payload.Request.LoginDto;
import com.teluguskillhub.springsecurity.Payload.Request.UserRequest;
import com.teluguskillhub.springsecurity.Service.UserService;
import com.teluguskillhub.springsecurity.security.JwtTokenProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/auth")
public class AuthController {

    @Autowired
    UserService userService;

    @Autowired
    JwtTokenProvider jwtTokenProvider;
    @Autowired
    AuthenticationManager authenticationManager;
    @PostMapping("/register")
    @ResponseStatus(HttpStatus.CREATED)
    public UserRequest createUser(@RequestBody UserRequest userRequest){
        return userService.createUser(userRequest);
    }

    @PostMapping("/login")
    @ResponseStatus(HttpStatus.OK)
    public String login(@RequestBody LoginDto loginDto){
        UsernamePasswordAuthenticationToken authenticationToken =
                new UsernamePasswordAuthenticationToken(loginDto.getEmail(), loginDto.getPassword());
        Authentication authentication = authenticationManager.authenticate(authenticationToken);
        System.out.println("-----------------------------------------");
        System.out.println("authentication :");
        System.out.println(authentication);
        //authentication object is(after login POST request):

        //UsernamePasswordAuthenticationToken [Principal=org.springframework.security.core.userdetails.User
        // [Username=suman@gmail.com, Password=[PROTECTED], Enabled=true, AccountNonExpired=true,
        // credentialsNonExpired=true,AccountNonLocked=true, Granted Authorities=[ROLE_ADMIN]],
        // Credentials=[PROTECTED],Authenticated=true, Details=null, Granted Authorities=[ROLE_ADMIN]]

        SecurityContext securityContext = SecurityContextHolder.getContext();
        securityContext.setAuthentication(authentication);
        //if it doesn't authenticate then throws an Exception.

        String token = jwtTokenProvider.generateToken(authentication);
        JwtAuthenticationResponse authenticationResponse = new JwtAuthenticationResponse(token);
        return "User logged in successfully with token:\n"+authenticationResponse;
    }
}
