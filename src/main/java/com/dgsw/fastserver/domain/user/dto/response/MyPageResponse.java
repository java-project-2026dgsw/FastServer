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

    private String studentId;

    private String subject;
}
