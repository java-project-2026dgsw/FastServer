package com.dgsw.fastserver.domain.user.exceptions;

import com.dgsw.fastserver.global.exception.status_code.StatusCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum UserStatusCode implements StatusCode {
    STUDENT_ID_ALREADY_EXISTS("STUDENT_ID_ALREADY_EXISTS", "이미 가입된 학번입니다.", HttpStatus.CONFLICT),
    TEACHER_ALREADY_EXISTS("TEACHER_ALREADY_EXISTS", "이미 가입된 선생님 계정입니다.", HttpStatus.CONFLICT),
    STUDENT_LOGIN_FAILED("STUDENT_LOGIN_FAILED", "학생 로그인 정보가 올바르지 않습니다.", HttpStatus.UNAUTHORIZED),
    TEACHER_LOGIN_FAILED("TEACHER_LOGIN_FAILED", "선생님 로그인 정보가 올바르지 않습니다.", HttpStatus.UNAUTHORIZED),
    USER_NOT_FOUND("USER_NOT_FOUND", "사용자를 찾을 수 없습니다.", HttpStatus.NOT_FOUND);

    private final String code;
    private final String message;
    private final HttpStatus httpStatus;
}
