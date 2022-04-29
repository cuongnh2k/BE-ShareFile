package vn.edu.cuongnh2k.berealtime.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import vn.edu.cuongnh2k.berealtime.basess.BaseResponseDto;
import vn.edu.cuongnh2k.berealtime.config.TokenConfig;
import vn.edu.cuongnh2k.berealtime.config.UserDetailServiceConfig;
import vn.edu.cuongnh2k.berealtime.dto.consumes.LoginConsumeDto;
import vn.edu.cuongnh2k.berealtime.dto.consumes.UserConsumeDto;
import vn.edu.cuongnh2k.berealtime.dto.produces.TokenProduceDto;
import vn.edu.cuongnh2k.berealtime.exceptions.BadRequestException;
import vn.edu.cuongnh2k.berealtime.services.DeviceService;
import vn.edu.cuongnh2k.berealtime.services.UserService;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequiredArgsConstructor
@RequestMapping("${base_api}/basic")
public class BasicController {

    private final AuthenticationManager authenticationManager;
    private final UserDetailServiceConfig mUserDetailServiceConfig;
    private final TokenConfig mTokenConfig;
    private final DeviceService mDeviceService;
    private final UserService mUserService;

    @PostMapping("/login")
    public ResponseEntity<BaseResponseDto> login(
            @RequestBody @Validated LoginConsumeDto loginConsumeDto,
            HttpServletRequest httpServletRequest) {
        UserDetails userDetails = mUserDetailServiceConfig.loadUserByUsername(loginConsumeDto.getEmail());
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                    userDetails.getUsername(),
                    loginConsumeDto.getPassword()));
        } catch (Exception e) {
            throw new BadRequestException("incorrect password");
        }
        TokenProduceDto tokenProduceDto = mTokenConfig.generateToken(userDetails, httpServletRequest);
        mDeviceService.updateToken(httpServletRequest, tokenProduceDto, userDetails.getUsername());
        return ResponseEntity.ok(BaseResponseDto.success(tokenProduceDto, "login successful"));
    }

    @PatchMapping("/refresh")
    public ResponseEntity<BaseResponseDto> refreshToken(HttpServletRequest httpServletRequest) {
        return ResponseEntity.ok(BaseResponseDto.success(
                mDeviceService.refreshToken(httpServletRequest),
                "refresh token successful"));
    }

    @PostMapping("/register")
    public ResponseEntity<BaseResponseDto> register(@RequestBody @Validated UserConsumeDto userConsumeDto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(
                BaseResponseDto.success(mUserService.register(userConsumeDto),
                        "register successful"));
    }

    @GetMapping("/user")
    public ResponseEntity<BaseResponseDto> getUserByEmail(
            @RequestParam(defaultValue = "0") Integer page,
            @RequestParam(defaultValue = "10") Integer size,
            @RequestParam(required = false) String sort,
            @RequestParam(defaultValue = "@gmail.com") String email) {
        return ResponseEntity.ok(BaseResponseDto.success(
                mUserService.searchUserByEmail(page, size, sort, email),
                "get data successful"));
    }
}
