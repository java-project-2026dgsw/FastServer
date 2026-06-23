package com.dgsw.fastserver.domain.user;

import com.dgsw.fastserver.domain.user.enums.UserRole;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "users")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    // 학생 전용: 학번
    private String studentId;

    // 선생님 전용: 담당 과목
    private String subject;

    @Enumerated(EnumType.STRING)
    private UserRole role;
}
