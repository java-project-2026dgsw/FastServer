package com.dgsw.fastserver.domain.volunteer.exceptions;

import com.dgsw.fastserver.global.exception.status_code.StatusCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum VolunteerStatusCode implements StatusCode {
    VOLUNTEER_NOT_FOUND("VOLUNTEER_NOT_FOUND", "봉사활동을 찾을 수 없습니다.", HttpStatus.NOT_FOUND);

    private final String code;
    private final String message;
    private final HttpStatus httpStatus;
}
