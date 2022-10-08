package com.example.besharefile.dto.consumes;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class LoginConsumeDto {

    private String email;

    private String password;
}
