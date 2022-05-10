package vn.edu.cuongnh2k.be_realtime.socket;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import vn.edu.cuongnh2k.be_realtime.socket.dto.Message;

@RestController
@RequiredArgsConstructor
public class WSController {

    private final WSService service;

    @PostMapping("/send-private-message/{channelId}")
    public void sendPrivateMessage(@PathVariable final Long channelId,
                                   @RequestBody final Message message) {
        service.notifyUser(channelId, message.getMessageContent());
    }
}
