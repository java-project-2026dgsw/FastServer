package com.dgsw.fastserver.domain.user.dto.response;

import com.dgsw.fastserver.domain.user.entity.User;
import com.dgsw.fastserver.domain.user.enums.UserRole;

public record UserSignupResponse(
        Long id,
        String name,
        String studentId,
        String subject,
        UserRole role
) {
    public static UserSignupResponse from(User user) {
        return new UserSignupResponse(
                user.getId(),
                user.getName(),
                user.getStudentId(),
                user.getSubject(),
                user.getRole()
        );
    }
}
