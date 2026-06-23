package com.dgsw.fastserver.domain.volunteer.service;

import com.dgsw.fastserver.domain.user.User;
import com.dgsw.fastserver.domain.user.repository.UserRepository;
import com.dgsw.fastserver.domain.volunteer.dto.response.VolunteerApplicationResponse;
import com.dgsw.fastserver.domain.volunteer.entity.VolunteerApplicationEntity;
import com.dgsw.fastserver.domain.volunteer.dto.request.VolunteerRequest;
import com.dgsw.fastserver.domain.volunteer.dto.response.VolunteerResponse;
import com.dgsw.fastserver.domain.volunteer.entity.VolunteerEntity;
import com.dgsw.fastserver.domain.volunteer.exceptions.VolunteerStatusCode;
import com.dgsw.fastserver.domain.volunteer.repository.VolunteerApplicationRepository;
import com.dgsw.fastserver.domain.volunteer.repository.VolunteerRepository;
import com.dgsw.fastserver.global.exception.exception.ApplicationException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class VolunteerService {

    private final VolunteerRepository volunteerRepository;
    private final VolunteerApplicationRepository volunteerApplicationRepository;
    private final UserRepository userRepository;

    public VolunteerResponse postVolunteer(VolunteerRequest volunteerRequest) {
        VolunteerEntity volunteer = new VolunteerEntity();
        volunteer.setVolunteerId(volunteerRequest.volunteerId());
        volunteer.setParticipantCount(volunteerRequest.participantCount());
        volunteer.setLocation(volunteerRequest.location());
        volunteer.setParticipationTime(volunteerRequest.participationTime());
        volunteer.setWorkStatus(volunteerRequest.workStatus());

        return VolunteerResponse.from(volunteerRepository.save(volunteer));
    }

    public List<VolunteerResponse> getVolunteers() {
        return volunteerRepository.findAll().stream()
                .map(VolunteerResponse::from)
                .collect(Collectors.toList());
    }

    public VolunteerResponse getVolunteer(Long id) {
        return VolunteerResponse.from(
                volunteerRepository.findById(id)
                        .orElseThrow(() -> ApplicationException.of(VolunteerStatusCode.VOLUNTEER_NOT_FOUND))
        );
    }

    public void deleteVolunteer(Long id) {
        if (!volunteerRepository.existsById(id)) {
            throw ApplicationException.of(VolunteerStatusCode.VOLUNTEER_NOT_FOUND);
        }
        volunteerRepository.deleteById(id);
    }

    public VolunteerResponse updateVolunteer(Long id, VolunteerRequest volunteerRequest) {
        VolunteerEntity volunteer = volunteerRepository.findById(id)
                .orElseThrow(() -> ApplicationException.of(VolunteerStatusCode.VOLUNTEER_NOT_FOUND));

        volunteer.setParticipantCount(volunteerRequest.participantCount());
        volunteer.setLocation(volunteerRequest.location());
        volunteer.setParticipationTime(volunteerRequest.participationTime());
        volunteer.setWorkStatus(volunteerRequest.workStatus());

        return VolunteerResponse.from(volunteerRepository.save(volunteer));
    }

    public VolunteerApplicationResponse applyVolunteer(Long volunteerId, Long userId) {
        VolunteerEntity volunteer = volunteerRepository.findById(volunteerId)
                .orElseThrow(() -> ApplicationException.of(VolunteerStatusCode.VOLUNTEER_NOT_FOUND));
        User user = userRepository.findById(userId)
                .orElseThrow(() -> ApplicationException.of(VolunteerStatusCode.VOLUNTEER_APPLICANT_NOT_FOUND));

        if (volunteerApplicationRepository.existsByVolunteer_IdAndUser_Id(volunteerId, userId)) {
            throw ApplicationException.of(VolunteerStatusCode.VOLUNTEER_ALREADY_APPLIED);
        }

        VolunteerApplicationEntity application = new VolunteerApplicationEntity();
        application.setVolunteer(volunteer);
        application.setUser(user);

        return VolunteerApplicationResponse.from(volunteerApplicationRepository.save(application));
    }

    public void cancelVolunteerApplication(Long volunteerId, Long userId) {
        if (!volunteerRepository.existsById(volunteerId)) {
            throw ApplicationException.of(VolunteerStatusCode.VOLUNTEER_NOT_FOUND);
        }

        if (!userRepository.existsById(userId)) {
            throw ApplicationException.of(VolunteerStatusCode.VOLUNTEER_APPLICANT_NOT_FOUND);
        }

        VolunteerApplicationEntity application = volunteerApplicationRepository.findByVolunteer_IdAndUser_Id(volunteerId, userId)
                .orElseThrow(() -> ApplicationException.of(VolunteerStatusCode.VOLUNTEER_APPLICATION_NOT_FOUND));

        volunteerApplicationRepository.delete(application);
    }
}
