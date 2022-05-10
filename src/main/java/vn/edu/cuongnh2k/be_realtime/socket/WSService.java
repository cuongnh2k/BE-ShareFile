package vn.edu.cuongnh2k.be_realtime.socket;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import vn.edu.cuongnh2k.be_realtime.exceptions.BadRequestException;
import vn.edu.cuongnh2k.be_realtime.repositories.UserChannelRepository;
import vn.edu.cuongnh2k.be_realtime.services.UserService;
import vn.edu.cuongnh2k.be_realtime.socket.dto.ResponseMessage;

@Service
@Transactional
public class WSService {

    private final SimpMessagingTemplate messagingTemplate;
    private final NotificationService notificationService;

    @Autowired
    public WSService(SimpMessagingTemplate messagingTemplate, NotificationService notificationService) {
        this.messagingTemplate = messagingTemplate;
        this.notificationService = notificationService;
    }

    @Autowired
    private UserService mUserService;

    @Autowired
    private UserChannelRepository mUserChannelRepository;

    public void notifyUser(final Long channelId, final String message) {
        ResponseMessage response = new ResponseMessage(message);
//        notificationService.sendPrivateNotification(email);
        if (!mUserChannelRepository.existsByUserIdAndChannelIdAndDeletedFlagFalse(
                mUserService.getCurrentUser().getId(),
                channelId)) {
            throw new BadRequestException("channel does not exist");
        }
        mUserChannelRepository.findByChannelIdAndDeletedFlagFalse(channelId).parallelStream().forEach(o ->
                messagingTemplate.convertAndSendToUser(o.getUser().getEmail(), "/topic/private-messages", response));
    }
}
