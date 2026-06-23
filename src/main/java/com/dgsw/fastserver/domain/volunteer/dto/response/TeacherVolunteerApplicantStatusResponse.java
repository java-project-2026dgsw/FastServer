package com.dgsw.fastserver.domain.volunteer.dto.response;

import com.dgsw.fastserver.domain.volunteer.entity.VolunteerApplicationEntity;

public record TeacherVolunteerApplicantStatusResponse(
        Long applicationId,
        Long volunteerId,
        String location,
        Long studentUserId,
        String studentName,
        String studentId
) {
    public static TeacherVolunteerApplicantStatusResponse from(VolunteerApplicationEntity application) {
        return new TeacherVolunteerApplicantStatusResponse(
                application.getId(),
                application.getVolunteer().getId(),
                application.getVolunteer().getLocation(),
                application.getUser().getId(),
                application.getUser().getName(),
                application.getUser().getStudentId()
        );
    }
}
