package com.dgsw.fastserver.domain.volunteer.repository;

import com.dgsw.fastserver.domain.volunteer.entity.VolunteerApplicationEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface VolunteerApplicationRepository extends JpaRepository<VolunteerApplicationEntity, Long> {
    boolean existsByVolunteer_IdAndUser_Id(Long volunteerId, Long userId);

    Optional<VolunteerApplicationEntity> findByVolunteer_IdAndUser_Id(Long volunteerId, Long userId);
}
