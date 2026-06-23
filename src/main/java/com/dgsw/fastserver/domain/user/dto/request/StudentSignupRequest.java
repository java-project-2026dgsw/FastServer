package com.dgsw.fastserver.domain.user.dto.request;

public record StudentSignupRequest(
        String name,
        String studentId,
        String password
) {
}
