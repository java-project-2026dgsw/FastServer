package com.dgsw.fastserver.domain.user.dto.request;

public record TeacherSignupRequest(
        String name,
        String subject,
        String password
) {
}
