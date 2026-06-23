package com.dgsw.fastserver.domain.user.controller;

import com.dgsw.fastserver.domain.user.dto.request.StudentSignupRequest;
import com.dgsw.fastserver.domain.user.dto.request.TeacherSignupRequest;
import com.dgsw.fastserver.domain.user.dto.response.UserSignupResponse;
import com.dgsw.fastserver.domain.user.service.UserAccountService;
import com.dgsw.fastserver.global.data.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserAccountController {

    private final UserAccountService userAccountService;

    @PostMapping("/signup/student")
    public ResponseEntity<ApiResponse<UserSignupResponse>> signupStudent(@RequestBody StudentSignupRequest request) {
        return ResponseEntity.ok(ApiResponse.ok(userAccountService.signupStudent(request)));
    }

    @PostMapping("/signup/teacher")
    public ResponseEntity<ApiResponse<UserSignupResponse>> signupTeacher(@RequestBody TeacherSignupRequest request) {
        return ResponseEntity.ok(ApiResponse.ok(userAccountService.signupTeacher(request)));
    }
}
