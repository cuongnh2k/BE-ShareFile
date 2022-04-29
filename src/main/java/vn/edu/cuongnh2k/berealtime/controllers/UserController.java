package vn.edu.cuongnh2k.berealtime.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import vn.edu.cuongnh2k.berealtime.basess.BaseResponseDto;
import vn.edu.cuongnh2k.berealtime.dto.consumes.UserConsumeDto;
import vn.edu.cuongnh2k.berealtime.services.UserService;

import java.io.IOException;

@RestController
@RequiredArgsConstructor
@RequestMapping("${base_api}/user")
public class UserController {

    private final UserService mUserService;

    @GetMapping
    public ResponseEntity<BaseResponseDto> getCurrentUser() {
        return ResponseEntity.ok(BaseResponseDto.success(mUserService.getAccount(), "get data successful"));
    }

    @PatchMapping
    public ResponseEntity<BaseResponseDto> editUser(@RequestBody @Validated UserConsumeDto userConsumeDto) {
        return ResponseEntity.ok(BaseResponseDto.success(
                mUserService.editUser(userConsumeDto),
                "get data successful"));
    }

    @PostMapping("/avatar")
    public ResponseEntity<BaseResponseDto> uploadAvatar(@RequestBody MultipartFile avatar) throws IOException {
        return ResponseEntity.status(HttpStatus.CREATED).body(BaseResponseDto.success(
                mUserService.addAvatar(avatar), "add avatar successful"));
    }
}
