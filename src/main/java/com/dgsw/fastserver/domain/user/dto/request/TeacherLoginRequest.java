package com.dgsw.fastserver.domain.user.dto.request;

public record TeacherLoginRequest(
        String name,
        String subject,
        String password
) {
}
