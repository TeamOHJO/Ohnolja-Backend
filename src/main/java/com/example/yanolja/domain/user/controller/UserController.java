package com.example.yanolja.domain.user.controller;

import com.example.yanolja.domain.user.dto.CreateUserRequest;
import com.example.yanolja.domain.user.dto.CreateUserResponse;
import com.example.yanolja.domain.user.entity.User;
import com.example.yanolja.domain.user.service.UserService;
import com.example.yanolja.global.springsecurity.PrincipalDetails;
import com.example.yanolja.global.util.ResponseDTO;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping("/signup")
    public ResponseEntity<ResponseDTO<Object>> signup(
        @RequestBody @Valid CreateUserRequest createUserRequest) {
        CreateUserResponse response = userService.signup(createUserRequest);
        ResponseDTO<Object> successResponse = ResponseDTO.res(
            HttpStatus.CREATED,
            "회원가입에 성공",
            response);

        return ResponseEntity.status(HttpStatus.CREATED).body(successResponse);
    }

    // Authenticated user 샘플테스트 코드입니다
    @GetMapping("/test")
    public ResponseEntity<?> test(@AuthenticationPrincipal PrincipalDetails principalDetails) {
        User user = principalDetails.getUser();

        CreateUserResponse createUserResponse = new CreateUserResponse(user.getEmail(),
            user.getUsername(), user.getPhonenumber());
        return ResponseEntity.ok(createUserResponse);
    }
}