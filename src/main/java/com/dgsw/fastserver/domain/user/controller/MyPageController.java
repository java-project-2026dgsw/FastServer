package com.dgsw.fastserver.domain.user.controller;

import com.dgsw.fastserver.domain.user.dto.request.MyPageUpdateRequest;
import com.dgsw.fastserver.domain.user.dto.response.MyPageResponse;
import com.dgsw.fastserver.domain.user.service.MyPageService;
import com.dgsw.fastserver.global.CommonResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/my")
public class MyPageController {

    @Autowired
    private MyPageService myPageService;

    @GetMapping("/info")
    public MyPageResponse getMyPageInfo(@RequestParam Long id) {
        log.info("마이페이지 조회 요청 - 사용자 ID: {}", id);
        return myPageService.getMyPageInfo(id);
    }

    @PutMapping("/update")
    public CommonResponse updateMyPageInfo(@RequestParam Long id, @RequestBody MyPageUpdateRequest request) {
        log.info("마이페이지 수정 요청 - 사용자 ID: {}", id);
        boolean updated = myPageService.updateMyPageInfo(id, request);
        if (!updated) {
            return new CommonResponse("사용자 정보 없음");
        }
        return new CommonResponse("수정 완료");
    }
}
