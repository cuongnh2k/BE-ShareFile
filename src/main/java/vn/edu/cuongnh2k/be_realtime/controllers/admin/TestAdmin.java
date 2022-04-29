package vn.edu.cuongnh2k.be_realtime.controllers.admin;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("${base_api}/admin")
public class TestAdmin {

    @GetMapping
    public ResponseEntity<?> admin() {
        return ResponseEntity.ok("admin");
    }
}
