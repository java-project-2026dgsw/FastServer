package com.dgsw.fastserver.domain.user.service;

import com.dgsw.fastserver.domain.user.dto.request.StudentSignupRequest;
import com.dgsw.fastserver.domain.user.dto.request.TeacherSignupRequest;
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
    public UserSignupResponse signupStudent(StudentSignupRequest request) {
        validateStudentSignup(request);

        User user = new User();
        user.setName(request.name().trim());
        user.setStudentId(request.studentId().trim());
        user.setSubject(null);
        user.setPassword(request.password().trim());
        user.setRole(UserRole.STUDENT);

        return UserSignupResponse.from(userRepository.save(user));
    }

    @Transactional
    public UserSignupResponse signupTeacher(TeacherSignupRequest request) {
        validateTeacherSignup(request);

        User user = new User();
        user.setName(request.name().trim());
        user.setStudentId(null);
        user.setSubject(request.subject().trim());
        user.setPassword(request.password().trim());
        user.setRole(UserRole.TEACHER);

        return UserSignupResponse.from(userRepository.save(user));
    }

    private void validateCommonFields(String name, String password) {
        if (isBlank(name)) {
            throw ApplicationException.of(CommonStatusCode.INVALID_ARGUMENT, "이름은 필수입니다.");
        }

        if (isBlank(password)) {
            throw ApplicationException.of(CommonStatusCode.INVALID_ARGUMENT, "비밀번호는 필수입니다.");
        }
    }

    private void validateStudentSignup(StudentSignupRequest request) {
        if (request == null) {
            throw ApplicationException.of(CommonStatusCode.INVALID_ARGUMENT, "회원가입 요청이 비어 있습니다.");
        }

        validateCommonFields(request.name(), request.password());

        if (isBlank(request.studentId())) {
            throw ApplicationException.of(CommonStatusCode.INVALID_ARGUMENT, "학생 회원가입에는 학번이 필요합니다.");
        }

        if (userRepository.existsByStudentId(request.studentId().trim())) {
            throw ApplicationException.of(UserStatusCode.STUDENT_ID_ALREADY_EXISTS);
        }
    }

    private void validateTeacherSignup(TeacherSignupRequest request) {
        if (request == null) {
            throw ApplicationException.of(CommonStatusCode.INVALID_ARGUMENT, "회원가입 요청이 비어 있습니다.");
        }

        validateCommonFields(request.name(), request.password());

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
