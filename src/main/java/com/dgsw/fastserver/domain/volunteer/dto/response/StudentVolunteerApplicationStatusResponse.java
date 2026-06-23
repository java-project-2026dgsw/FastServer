package com.dgsw.fastserver.domain.volunteer.dto.response;

import com.dgsw.fastserver.domain.volunteer.entity.VolunteerApplicationEntity;
import com.dgsw.fastserver.domain.volunteer.enums.WorkStatus;

import java.time.LocalDateTime;

public record StudentVolunteerApplicationStatusResponse(
        Long applicationId,
        Long volunteerId,
        String location,
        LocalDateTime participationTime,
        WorkStatus workStatus
) {
    public static StudentVolunteerApplicationStatusResponse from(VolunteerApplicationEntity application) {
        return new StudentVolunteerApplicationStatusResponse(
                application.getId(),
                application.getVolunteer().getId(),
                application.getVolunteer().getLocation(),
                application.getVolunteer().getParticipationTime(),
                application.getVolunteer().getWorkStatus()
        );
    }
}
