package com.example.w7_miniproject_backend.controller;

import com.example.w7_miniproject_backend.dto.userDto.SignupRequestDto;
import com.example.w7_miniproject_backend.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    //회원가입
    @PostMapping("/user/signup")
    public ResponseEntity registerUser(@RequestBody SignupRequestDto signupRequestDto){
        return userService.registerUser(signupRequestDto);
    }

<<<<<<< Updated upstream
=======
    // 닉네임 중복 확인
    @PostMapping("/user/idCheck")
    public boolean nicknameCheck(@RequestBody SignupRequestDto signupRequestDto) {
        return userService.nicknameCheck(signupRequestDto.getNickname());
    }

    // 카카오 소셜 로그인
    @GetMapping("/user/kakao/callback")
    public ResponseEntity<KakaoUserResponseDto> kakaoLogin(@RequestParam String code, HttpServletResponse response) throws JsonProcessingException {
        KakaoUserResponseDto kakaoUserResponseDto = kakaoUserService.kakaoLogin(code, response);
        return ResponseEntity.ok().body(kakaoUserResponseDto);
    }
    @GetMapping("/post/residence={residence}/")
>>>>>>> Stashed changes
}
