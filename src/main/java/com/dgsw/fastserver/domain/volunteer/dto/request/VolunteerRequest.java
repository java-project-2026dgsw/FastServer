package com.dgsw.fastserver.domain.volunteer.dto.request;

import com.dgsw.fastserver.domain.volunteer.enums.WorkStatus;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

public record VolunteerRequest(
        Long volunteerId,
        int participantCount,
        String location,
        LocalDateTime participationTime,
        WorkStatus workStatus
) {
}
