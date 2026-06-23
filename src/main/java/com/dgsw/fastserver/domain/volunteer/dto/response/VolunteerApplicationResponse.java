package com.dgsw.fastserver.domain.volunteer.dto.response;

import com.dgsw.fastserver.domain.volunteer.entity.VolunteerApplicationEntity;

public record VolunteerApplicationResponse(
        Long id,
        Long volunteerId,
        Long userId
) {
    public static VolunteerApplicationResponse from(VolunteerApplicationEntity application) {
        return new VolunteerApplicationResponse(
                application.getId(),
                application.getVolunteer().getId(),
                application.getUser().getId()
        );
    }
}
