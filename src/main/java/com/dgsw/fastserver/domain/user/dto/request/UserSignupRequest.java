package com.dgsw.fastserver.domain.user.dto.request;

import com.dgsw.fastserver.domain.user.enums.UserRole;

public record UserSignupRequest(
        String name,
        String studentId,
        String subject,
        String password,
        UserRole role
) {
}
