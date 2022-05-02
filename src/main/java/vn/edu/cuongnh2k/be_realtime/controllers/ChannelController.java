package vn.edu.cuongnh2k.be_realtime.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import vn.edu.cuongnh2k.be_realtime.basess.BaseResponseDto;
import vn.edu.cuongnh2k.be_realtime.services.ChannelService;

@RestController
@RequiredArgsConstructor
@RequestMapping("${base_api}/channel")
public class ChannelController {

    private final ChannelService mChannelService;

    @GetMapping("/user")
    public ResponseEntity<BaseResponseDto> getChannelByUser(
            @RequestParam(defaultValue = "0") Integer page,
            @RequestParam(defaultValue = "10") Integer size,
            @RequestParam(required = false) String sort) {
        return ResponseEntity.ok(BaseResponseDto.success(
                mChannelService.getChannelByUser(page, size, sort),
                "get data successful"));
    }
}
