package com.dgsw.fastserver.domain.user.repository;

import com.dgsw.fastserver.domain.user.User;
import com.dgsw.fastserver.domain.user.enums.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    boolean existsByStudentId(String studentId);

    boolean existsByRoleAndNameAndSubject(UserRole role, String name, String subject);
}
