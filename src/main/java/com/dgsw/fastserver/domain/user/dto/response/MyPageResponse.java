package com.dgsw.fastserver.domain.user.dto.response;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MyPageResponse {
    private Long id;
    private String name;
    private String role;

    // 학생 전용 필드
    private String studentId;

    // 선생님 전용 필드
    private String subject;
}
