package com.dgsw.fastserver.domain.volunteer.exceptions;

import com.dgsw.fastserver.global.exception.status_code.StatusCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum VolunteerStatusCode implements StatusCode {
    VOLUNTEER_NOT_FOUND("VOLUNTEER_NOT_FOUND", "봉사활동을 찾을 수 없습니다.", HttpStatus.NOT_FOUND),
    VOLUNTEER_APPLICANT_NOT_FOUND("VOLUNTEER_APPLICANT_NOT_FOUND", "지원자를 찾을 수 없습니다.", HttpStatus.NOT_FOUND),
    VOLUNTEER_ALREADY_APPLIED("VOLUNTEER_ALREADY_APPLIED", "이미 지원한 심부름입니다.", HttpStatus.BAD_REQUEST),
    VOLUNTEER_APPLICATION_NOT_FOUND("VOLUNTEER_APPLICATION_NOT_FOUND", "심부름 지원 내역을 찾을 수 없습니다.", HttpStatus.NOT_FOUND);

    private final String code;
    private final String message;
    private final HttpStatus httpStatus;
}
