package com.example.besharefile.controllers;

import com.example.besharefile.basess.BaseResponseDto;
import com.example.besharefile.config.TokenConfig;
import com.example.besharefile.config.UserDetailServiceConfig;
import com.example.besharefile.dto.consumes.LoginConsumeDto;
import com.example.besharefile.exceptions.BadRequestException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequiredArgsConstructor
@RequestMapping("${base_api}/basic")
public class BasicController {

    private final AuthenticationManager authenticationManager;

    private final UserDetailServiceConfig mUserDetailServiceConfig;

    private final TokenConfig mTokenConfig;

    private final HttpServletRequest request;

    @PostMapping("/login")
    public ResponseEntity<BaseResponseDto> login(@RequestBody LoginConsumeDto consume) {
        UserDetails userDetails = mUserDetailServiceConfig.loadUserByUsername(consume.getEmail());
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                    userDetails.getUsername(),
                    consume.getPassword()));
        } catch (Exception e) {
            throw new BadRequestException("incorrect password");
        }
        return ResponseEntity.ok(BaseResponseDto.success(
                mTokenConfig.generateToken(userDetails, request),
                "login successful"));
    }
}
