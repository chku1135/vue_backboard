package com.vue_test.test.controller;

import com.vue_test.test.entity.User;
import com.vue_test.test.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final UserRepository userRepository;

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody User user) {
        if (userRepository.existsByUsername(user.getUsername())) {
            return ResponseEntity.badRequest().body("이미 존재하는 사용자명입니다.");
        }
        userRepository.save(user);
        return ResponseEntity.ok("회원가입 성공");
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody Map<String, String> credentials) {
        String username = credentials.get("username");
        String password = credentials.get("password");

        return userRepository.findByUsername(username)
                .filter(user -> user.getPassword().equals(password))
                .map(user -> ResponseEntity.ok(Map.of(
                        "message", "로그인 성공",
                        "username", user.getUsername()
                )))
                .orElse(ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                        .body(Map.of("message", "사용자명 또는 비밀번호가 올바르지 않습니다.")));
    }
}
