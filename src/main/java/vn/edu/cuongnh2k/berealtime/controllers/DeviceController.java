package vn.edu.cuongnh2k.berealtime.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import vn.edu.cuongnh2k.berealtime.basess.BaseResponseDto;
import vn.edu.cuongnh2k.berealtime.dto.consumes.LogoutConsumeDto;
import vn.edu.cuongnh2k.berealtime.services.DeviceService;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequiredArgsConstructor
@RequestMapping("${base_api}/device")
public class DeviceController {

    private final DeviceService mDeviceService;

    @DeleteMapping("/logouts")
    public ResponseEntity<BaseResponseDto> logouts(@RequestBody @Validated LogoutConsumeDto logoutConsumeDto) {
        mDeviceService.logouts(logoutConsumeDto.getDeviceIds());
        return ResponseEntity.ok(BaseResponseDto.success("logout successful"));
    }

    @DeleteMapping("/logout")
    public ResponseEntity<BaseResponseDto> logout(HttpServletRequest httpServletRequest) {
        mDeviceService.logout(httpServletRequest);
        return ResponseEntity.ok(BaseResponseDto.success("logout successful"));
    }

    @GetMapping("/user")
    public ResponseEntity<BaseResponseDto> getDeviceByUserId(
            @RequestParam(defaultValue = "0") Integer page,
            @RequestParam(defaultValue = "10") Integer size,
            @RequestParam(required = false) String sort) {
        return ResponseEntity.ok(BaseResponseDto.success(
                mDeviceService.getDeviceByUser(page, size, sort),
                "logout successful"));
    }
}
