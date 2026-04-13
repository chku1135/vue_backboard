package com.vue_test.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/api")
@Tag(name = "Hello", description = "테스트용 API")
public class HelloController {

    @GetMapping("/hello")
    @Operation(summary = "인사말 반환", description = "백엔드 연결 확인용 API입니다.")
    public String hello() {
        return "Hello from Spring Boot!";
    }
}
