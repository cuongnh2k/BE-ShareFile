package com.example.besharefile.dto.produces;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class LoginProduceDto {

    private String accessToken;

    private String refreshToken;
}
