package com.dgsw.fastserver.domain.volunteer.service;

import com.dgsw.fastserver.domain.volunteer.dto.request.VolunteerRequest;
import com.dgsw.fastserver.domain.volunteer.dto.response.VolunteerResponse;
import com.dgsw.fastserver.domain.volunteer.entity.VolunteerEntity;
import com.dgsw.fastserver.domain.volunteer.repository.VolunteerRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class VolunteerService {

    VolunteerRepository volunteerRepository;

    public VolunteerResponse postVolunteers(VolunteerRequest volunteerRequest) {
        VolunteerEntity volunteer = new VolunteerEntity();
        volunteer.setVolunteerId(volunteerRequest.volunteerId());
        volunteer.setParticipantCount(volunteerRequest.participantCount());
        volunteer.setLocation(volunteerRequest.location());
        volunteer.setParticipationTime(volunteerRequest.participationTime());
        volunteer.setWorkStatus(volunteerRequest.workStatus());

        volunteerRepository.save(volunteer);

        return VolunteerResponse.from(volunteer);
    }

    public List<VolunteerResponse> getVolunteers() {
        return volunteerRepository.findAll().stream()
                .map(VolunteerResponse::from)
                .collect(Collectors.toList());
    }

    public VolunteerResponse getVolunteer(Long id) {
        return VolunteerResponse.from(volunteerRepository.findById(id).orElseThrow());
    }

    public void deleteVolunteer(Long id) {
        volunteerRepository.deleteById(id);
    }

    public void updateVolunteer(Long id, VolunteerRequest volunteerRequest) {
        volunteerRepository.findById(id).ifPresent(volunteer -> {
            volunteer.setParticipantCount(volunteerRequest.participantCount());
            volunteer.setLocation(volunteerRequest.location());
            volunteer.setParticipationTime(volunteerRequest.participationTime());
            volunteer.setWorkStatus(volunteerRequest.workStatus());
            volunteerRepository.save(volunteer);
        });
    }
}
