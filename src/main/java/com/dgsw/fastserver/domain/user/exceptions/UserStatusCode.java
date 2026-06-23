package com.dgsw.fastserver.domain.user.exceptions;

import com.dgsw.fastserver.global.exception.status_code.StatusCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum UserStatusCode implements StatusCode {
    STUDENT_ID_ALREADY_EXISTS("STUDENT_ID_ALREADY_EXISTS", "이미 가입된 학번입니다.", HttpStatus.CONFLICT),
    TEACHER_ALREADY_EXISTS("TEACHER_ALREADY_EXISTS", "이미 가입된 선생님 계정입니다.", HttpStatus.CONFLICT);

    private final String code;
    private final String message;
    private final HttpStatus httpStatus;
}
