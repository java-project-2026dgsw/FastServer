package com.dgsw.fastserver.domain.volunteer.dto.response;

import com.dgsw.fastserver.domain.volunteer.entity.VolunteerEntity;
import com.dgsw.fastserver.domain.volunteer.enums.WorkStatus;

import java.time.LocalDateTime;

public record VolunteerResponse(
        Long id,
        int participantCount,
        String location,
        LocalDateTime participationTime,
        WorkStatus workStatus
) {
    public static VolunteerResponse from(VolunteerEntity volunteer) {
        return new VolunteerResponse(
                volunteer.getId(),
                volunteer.getParticipantCount(),
                volunteer.getLocation(),
                volunteer.getParticipationTime(),
                volunteer.getWorkStatus()
        );
    }
}
