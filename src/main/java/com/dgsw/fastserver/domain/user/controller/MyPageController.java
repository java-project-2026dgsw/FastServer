package com.dgsw.fastserver.domain.user.controller;

import com.dgsw.fastserver.domain.user.dto.request.MyPageUpdateRequest;
import com.dgsw.fastserver.domain.user.dto.response.MyPageResponse;
import com.dgsw.fastserver.domain.user.service.MyPageService;
import com.dgsw.fastserver.global.data.ApiResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/my")
public class MyPageController {

    @Autowired
    private MyPageService myPageService;

    @GetMapping("/info")
    public ResponseEntity<ApiResponse<MyPageResponse>> getMyPageInfo(@RequestParam Long id) {
        log.info("마이페이지 조회 요청 - 사용자 ID: {}", id);
        MyPageResponse response = myPageService.getMyPageInfo(id);
        return ResponseEntity.ok(ApiResponse.ok(response));
    }

    @PutMapping("/update")
    public ResponseEntity<ApiResponse<Void>> updateMyPageInfo(@RequestParam Long id,
                                                              @RequestBody MyPageUpdateRequest request) {
        log.info("마이페이지 수정 요청 - 사용자 ID: {}", id);
        myPageService.updateMyPageInfo(id, request);
        return ResponseEntity.ok(ApiResponse.ok(null));
    }
}
