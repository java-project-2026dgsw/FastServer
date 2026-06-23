package com.dgsw.fastserver.domain.user.service;

import com.dgsw.fastserver.domain.user.dto.request.UserSignupRequest;
import com.dgsw.fastserver.domain.user.dto.response.UserSignupResponse;
import com.dgsw.fastserver.domain.user.entity.User;
import com.dgsw.fastserver.domain.user.enums.UserRole;
import com.dgsw.fastserver.domain.user.exceptions.UserStatusCode;
import com.dgsw.fastserver.domain.user.repository.UserRepository;
import com.dgsw.fastserver.global.exception.exception.ApplicationException;
import com.dgsw.fastserver.global.exception.status_code.CommonStatusCode;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UserAccountService {

    private final UserRepository userRepository;

    @Transactional
    public UserSignupResponse signup(UserSignupRequest request) {
        validateCommonFields(request);

        if (request.role() == UserRole.STUDENT) {
            validateStudentSignup(request);
        } else {
            validateTeacherSignup(request);
        }

        User user = new User();
        user.setName(request.name().trim());
        user.setPassword(request.password().trim());
        user.setRole(request.role());

        if (request.role() == UserRole.STUDENT) {
            user.setStudentId(request.studentId().trim());
            user.setSubject(null);
        } else {
            user.setStudentId(null);
            user.setSubject(request.subject().trim());
        }

        return UserSignupResponse.from(userRepository.save(user));
    }

    private void validateCommonFields(UserSignupRequest request) {
        if (request == null) {
            throw ApplicationException.of(CommonStatusCode.INVALID_ARGUMENT, "회원가입 요청이 비어 있습니다.");
        }

        if (request.role() == null) {
            throw ApplicationException.of(CommonStatusCode.INVALID_ARGUMENT, "사용자 역할은 필수입니다.");
        }

        if (isBlank(request.name())) {
            throw ApplicationException.of(CommonStatusCode.INVALID_ARGUMENT, "이름은 필수입니다.");
        }

        if (isBlank(request.password())) {
            throw ApplicationException.of(CommonStatusCode.INVALID_ARGUMENT, "비밀번호는 필수입니다.");
        }
    }

    private void validateStudentSignup(UserSignupRequest request) {
        if (isBlank(request.studentId())) {
            throw ApplicationException.of(CommonStatusCode.INVALID_ARGUMENT, "학생 회원가입에는 학번이 필요합니다.");
        }

        if (userRepository.existsByStudentId(request.studentId().trim())) {
            throw ApplicationException.of(UserStatusCode.STUDENT_ID_ALREADY_EXISTS);
        }
    }

    private void validateTeacherSignup(UserSignupRequest request) {
        if (isBlank(request.subject())) {
            throw ApplicationException.of(CommonStatusCode.INVALID_ARGUMENT, "선생님 회원가입에는 담당 과목이 필요합니다.");
        }

        if (userRepository.existsByRoleAndNameAndSubject(
                UserRole.TEACHER,
                request.name().trim(),
                request.subject().trim())) {
            throw ApplicationException.of(UserStatusCode.TEACHER_ALREADY_EXISTS);
        }
    }

    private boolean isBlank(String value) {
        return value == null || value.trim().isEmpty();
    }
}
