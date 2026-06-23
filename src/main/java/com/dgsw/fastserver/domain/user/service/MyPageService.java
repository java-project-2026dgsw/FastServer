package com.dgsw.fastserver.domain.user.service;

import com.dgsw.fastserver.domain.user.User;
import com.dgsw.fastserver.domain.user.dto.request.MyPageUpdateRequest;
import com.dgsw.fastserver.domain.user.dto.response.MyPageResponse;
import com.dgsw.fastserver.domain.user.enums.UserRole;
import com.dgsw.fastserver.domain.user.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Slf4j
public class MyPageService {
    @Autowired
    UserRepository userRepository;

    public MyPageResponse getMyPageInfo(Long id) {
        log.info("마이페이지 조회 - 사용자 ID: {}", id);

        Optional<User> optional = userRepository.findById(id);
        if (optional.isPresent()) {
            User user = optional.get();

            MyPageResponse response = new MyPageResponse();
            response.setId(user.getId());
            response.setName(user.getName());
            response.setRole(user.getRole().name());

            if (user.getRole() == UserRole.TEACHER) {
                log.info("선생님 마이페이지 조회 - ID: {}", id);
                response.setSubject(user.getSubject());
            } else {
                log.info("학생 마이페이지 조회 - ID: {}", id);
                response.setStudentId(user.getStudentId());
            }

            return response;
        }
        log.warn("사용자 정보 없음 - ID: {}", id);
        return null;
    }

    public boolean updateMyPageInfo(Long id, MyPageUpdateRequest request) {
        log.info("마이페이지 수정 - 사용자 ID: {}", id);

        Optional<User> optional = userRepository.findById(id);
        if (optional.isPresent()) {
            User user = optional.get();

            if (request.getName() != null && !request.getName().isEmpty()) {
                user.setName(request.getName());
                log.info("이름 수정 - {}", request.getName());
            }
            if (user.getRole() == UserRole.TEACHER) {
                if (request.getSubject() != null && !request.getSubject().isEmpty()) {
                    user.setSubject(request.getSubject());
                    log.info("담당 과목 수정 - {}", request.getSubject());
                }
            } else {
                if (request.getStudentId() != null && !request.getStudentId().isEmpty()) {
                    user.setStudentId(request.getStudentId());
                    log.info("학번 수정 - {}", request.getStudentId());
                }
            }

            userRepository.save(user);
            log.info("마이페이지 수정 완료 - 사용자 ID: {}", id);
            return true;
        }
        log.warn("사용자 정보 없음 - ID: {}", id);
        return false;
    }
}
