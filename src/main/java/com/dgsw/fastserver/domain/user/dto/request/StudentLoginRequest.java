package com.dgsw.fastserver.domain.user.dto.request;

public record StudentLoginRequest(
        String studentId,
        String password
) {
}
