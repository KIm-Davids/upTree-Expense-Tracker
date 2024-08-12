package com.semicolon.africa.web.controllers;

import com.semicolon.africa.dtos.request.LoginRequest;
import com.semicolon.africa.dtos.request.RegisterUserRequest;
import com.semicolon.africa.dtos.response.ApiResponse;
import com.semicolon.africa.dtos.response.LoginResponse;
import com.semicolon.africa.dtos.response.RegisterUserResponse;
import com.semicolon.africa.exceptions.MyExceptionClass;
import com.semicolon.africa.services.UserServices;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.HttpStatus.*;

@RestController
@RequestMapping("/api/v1/user")
@AllArgsConstructor
public class UserController {

    private UserServices userService;

    @PostMapping("/register")
    public ResponseEntity<?> createUser(@RequestBody RegisterUserRequest request){
        try{
            RegisterUserResponse response = userService.registerUserWith(request);
            return new ResponseEntity<>(new ApiResponse(true,response), CREATED);
        }
        catch (MyExceptionClass exception){
            return new ResponseEntity<>(new ApiResponse(false,exception.getMessage()), CREATED);
        }
    }

    @PatchMapping()
    public ResponseEntity<?> loginUser(@RequestBody LoginRequest loginRequest){
        try{
            LoginResponse response = userService.login(loginRequest);
            return new ResponseEntity<>(new ApiResponse(true, response), OK);
        }catch(MyExceptionClass exception){
            return new ResponseEntity<>(new ApiResponse(false, exception), BAD_GATEWAY);
        }
    }


}



