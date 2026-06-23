package com.dgsw.fastserver.domain.user.dto.response;

import lombok.*;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class MyPageResponse {
    private Long id;
    private String name;
    private String role;
    private String studentId;
    private String subject;

    public void setId(Long id) { this.id = id; }
    public void setName(String name) { this.name = name; }
    public void setRole(String role) { this.role = role; }
    public void setStudentId(String studentId) { this.studentId = studentId; }
    public void setSubject(String subject) { this.subject = subject; }
}
