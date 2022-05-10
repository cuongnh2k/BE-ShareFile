package vn.edu.cuongnh2k.be_realtime.socket;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;
import vn.edu.cuongnh2k.be_realtime.socket.dto.ResponseMessage;

@Service
public class NotificationService {
    private final SimpMessagingTemplate messagingTemplate;

    @Autowired
    public NotificationService(SimpMessagingTemplate messagingTemplate) {
        this.messagingTemplate = messagingTemplate;
    }

    public void sendPrivateNotification(final String email) {
        ResponseMessage message = new ResponseMessage("Private Notification");
        messagingTemplate.convertAndSendToUser(email, "/topic/private-notifications", message);
    }
}
