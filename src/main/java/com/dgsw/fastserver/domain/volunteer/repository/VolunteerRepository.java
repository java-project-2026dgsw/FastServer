package com.dgsw.fastserver.domain.volunteer.repository;

import com.dgsw.fastserver.domain.volunteer.entity.VolunteerEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VolunteerRepository extends JpaRepository<VolunteerEntity, Long> {
    List<VolunteerEntity> findAllByAuthorUserId(Long authorUserId);
}
