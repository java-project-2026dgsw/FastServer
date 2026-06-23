package com.dgsw.fastserver.domain.user.service;

import com.dgsw.fastserver.domain.user.dto.request.StudentLoginRequest;
import com.dgsw.fastserver.domain.user.dto.request.StudentSignupRequest;
import com.dgsw.fastserver.domain.user.dto.request.TeacherLoginRequest;
import com.dgsw.fastserver.domain.user.dto.request.TeacherSignupRequest;
import com.dgsw.fastserver.domain.user.dto.response.UserSignupResponse;
import com.dgsw.fastserver.domain.user.entity.User;
import com.dgsw.fastserver.domain.user.enums.UserRole;
import com.dgsw.fastserver.domain.user.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

@SpringBootTest(properties = {
        "spring.datasource.url=jdbc:h2:mem:user-account-test;DB_CLOSE_DELAY=-1",
        "spring.jpa.hibernate.ddl-auto=create-drop"
})
@Transactional
class UserAccountServiceTest {

    @Autowired
    private UserAccountService userAccountService;

    @Autowired
    private UserRepository userRepository;

    @Test
    void signupStudentCreatesStudentAccountWithoutSubject() {
        UserSignupResponse response = userAccountService.signupStudent(
                new StudentSignupRequest("홍길동", "2401", "1234")
        );

        User savedUser = userRepository.findById(response.id()).orElseThrow();

        assertEquals(UserRole.STUDENT, response.role());
        assertEquals("2401", response.studentId());
        assertNull(response.subject());
        assertEquals(UserRole.STUDENT, savedUser.getRole());
        assertEquals("2401", savedUser.getStudentId());
        assertNull(savedUser.getSubject());
    }

    @Test
    void signupTeacherCreatesTeacherAccountWithoutStudentId() {
        UserSignupResponse response = userAccountService.signupTeacher(
                new TeacherSignupRequest("김선생", "수학", "1234")
        );

        User savedUser = userRepository.findById(response.id()).orElseThrow();

        assertEquals(UserRole.TEACHER, response.role());
        assertEquals("수학", response.subject());
        assertNull(response.studentId());
        assertEquals(UserRole.TEACHER, savedUser.getRole());
        assertEquals("수학", savedUser.getSubject());
        assertNull(savedUser.getStudentId());
    }

    @Test
    void loginStudentReturnsMatchingStudentAccount() {
        userAccountService.signupStudent(new StudentSignupRequest("홍길동", "2401", "1234"));

        UserSignupResponse response = userAccountService.loginStudent(
                new StudentLoginRequest("2401", "1234")
        );

        assertEquals(UserRole.STUDENT, response.role());
        assertEquals("홍길동", response.name());
        assertEquals("2401", response.studentId());
        assertNull(response.subject());
    }

    @Test
    void loginTeacherReturnsMatchingTeacherAccount() {
        userAccountService.signupTeacher(new TeacherSignupRequest("김선생", "수학", "1234"));

        UserSignupResponse response = userAccountService.loginTeacher(
                new TeacherLoginRequest("김선생", "수학", "1234")
        );

        assertEquals(UserRole.TEACHER, response.role());
        assertEquals("김선생", response.name());
        assertEquals("수학", response.subject());
        assertNull(response.studentId());
    }
}
