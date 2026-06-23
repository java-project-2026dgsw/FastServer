package com.dgsw.fastserver.domain.volunteer.service;

import com.dgsw.fastserver.domain.user.entity.User;
import com.dgsw.fastserver.domain.user.enums.UserRole;
import com.dgsw.fastserver.domain.user.repository.UserRepository;
import com.dgsw.fastserver.domain.volunteer.dto.response.StudentVolunteerApplicationStatusResponse;
import com.dgsw.fastserver.domain.volunteer.dto.response.TeacherVolunteerApplicantStatusResponse;
import com.dgsw.fastserver.domain.volunteer.entity.VolunteerApplicationEntity;
import com.dgsw.fastserver.domain.volunteer.entity.VolunteerEntity;
import com.dgsw.fastserver.domain.volunteer.enums.WorkStatus;
import com.dgsw.fastserver.domain.volunteer.repository.VolunteerApplicationRepository;
import com.dgsw.fastserver.domain.volunteer.repository.VolunteerRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@Transactional
class VolunteerServiceTest {

    @Autowired
    private VolunteerService volunteerService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private VolunteerRepository volunteerRepository;

    @Autowired
    private VolunteerApplicationRepository volunteerApplicationRepository;

    @Test
    void getMyVolunteerApplicationsReturnsAppliedVolunteerStatuses() {
        User teacher = saveTeacher("김선생", "수학");
        User student = saveStudent("홍길동", "2401");
        VolunteerEntity volunteer = saveVolunteer(teacher.getId(), "본관 1층", WorkStatus.PENDING);
        saveApplication(volunteer, student);

        List<StudentVolunteerApplicationStatusResponse> responses =
                volunteerService.getMyVolunteerApplications(student.getId());

        assertEquals(1, responses.size());
        StudentVolunteerApplicationStatusResponse response = responses.getFirst();
        assertEquals(volunteer.getId(), response.volunteerId());
        assertEquals("본관 1층", response.location());
        assertEquals(WorkStatus.PENDING, response.workStatus());
    }

    @Test
    void getReceivedVolunteerApplicationsReturnsStudentsForTeachersErrands() {
        User teacher = saveTeacher("박선생", "과학");
        User student = saveStudent("이학생", "2502");
        VolunteerEntity volunteer = saveVolunteer(teacher.getId(), "교무실", WorkStatus.IN_PROGRESS);
        saveApplication(volunteer, student);

        List<TeacherVolunteerApplicantStatusResponse> responses =
                volunteerService.getReceivedVolunteerApplications(teacher.getId());

        assertEquals(1, responses.size());
        TeacherVolunteerApplicantStatusResponse response = responses.getFirst();
        assertEquals(volunteer.getId(), response.volunteerId());
        assertEquals(student.getId(), response.studentUserId());
        assertEquals("이학생", response.studentName());
        assertEquals("2502", response.studentId());
    }

    private User saveTeacher(String name, String subject) {
        User user = new User();
        user.setName(name);
        user.setSubject(subject);
        user.setPassword("1234");
        user.setRole(UserRole.TEACHER);
        return userRepository.save(user);
    }

    private User saveStudent(String name, String studentId) {
        User user = new User();
        user.setName(name);
        user.setStudentId(studentId);
        user.setPassword("1234");
        user.setRole(UserRole.STUDENT);
        return userRepository.save(user);
    }

    private VolunteerEntity saveVolunteer(Long authorUserId, String location, WorkStatus workStatus) {
        VolunteerEntity volunteer = new VolunteerEntity();
        volunteer.setAuthorUserId(authorUserId);
        volunteer.setParticipantCount(1);
        volunteer.setLocation(location);
        volunteer.setParticipationTime(LocalDateTime.of(2026, 6, 23, 17, 0));
        volunteer.setWorkStatus(workStatus);
        return volunteerRepository.save(volunteer);
    }

    private void saveApplication(VolunteerEntity volunteer, User student) {
        VolunteerApplicationEntity application = new VolunteerApplicationEntity();
        application.setVolunteer(volunteer);
        application.setUser(student);
        volunteerApplicationRepository.save(application);
    }
}
