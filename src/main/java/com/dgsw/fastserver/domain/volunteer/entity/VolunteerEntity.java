package com.dgsw.fastserver.domain.volunteer.entity;

import com.dgsw.fastserver.domain.volunteer.enums.WorkStatus;
import com.dgsw.fastserver.global.entity.Base;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;
import org.springframework.data.annotation.Id;

import java.time.LocalDateTime;

@Entity(name = "volunteer")
@Getter
@Setter
public class VolunteerEntity extends Base {

    @Id
    @Column(name = "volunteer_id")
    private Long volunteerId;

    @Column(name = "participant_count")
    @ColumnDefault(value = "0")
    private int participantCount;

    @Column(name = "location")
    private String location;

    @Column(name = "participation_time")
    private LocalDateTime participationTime;

    @Column(name = "work_status")
    private WorkStatus workStatus;

    @Column(name = "is_active")
    @ColumnDefault(value = "true")
    private boolean isActive;

    @Column(name = "is_deleted")
    @ColumnDefault(value = "false")
    private boolean isDeleted;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;
}
