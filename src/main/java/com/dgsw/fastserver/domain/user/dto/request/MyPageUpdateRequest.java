package com.dgsw.fastserver.domain.user.dto.request;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MyPageUpdateRequest {
    private String name;
    private String studentId;
    private String subject;
}
