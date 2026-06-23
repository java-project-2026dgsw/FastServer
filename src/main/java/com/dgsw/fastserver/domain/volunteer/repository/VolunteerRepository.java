package com.dgsw.fastserver.domain.volunteer.repository;

import com.dgsw.fastserver.domain.volunteer.entity.VolunteerEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;

@Repository
public interface VolunteerRepository extends JpaRepository<VolunteerEntity, Long> {
    VolunteerEntity findByVolunteerIdAndParticipantCountAndLocationAndParticipationTime(
            Long volunteerId, int participantCount, String location, LocalDateTime participationTime
    );}
