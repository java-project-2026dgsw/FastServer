package com.dgsw.fastserver.domain.user.controller;

import com.dgsw.fastserver.domain.user.dto.request.UserSignupRequest;
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

    @PostMapping("/signup")
    public ResponseEntity<ApiResponse<UserSignupResponse>> signup(@RequestBody UserSignupRequest request) {
        return ResponseEntity.ok(ApiResponse.ok(userAccountService.signup(request)));
    }
}
