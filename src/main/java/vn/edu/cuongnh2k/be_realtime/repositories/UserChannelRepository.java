package vn.edu.cuongnh2k.be_realtime.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import vn.edu.cuongnh2k.be_realtime.entities.UserChannelEntity;

import java.util.List;

@Repository
public interface UserChannelRepository extends JpaRepository<UserChannelEntity, Long> {

    Boolean existsByUserIdAndChannelIdAndDeletedFlagFalse(Long userId, Long channelId);

    List<UserChannelEntity> findByChannelIdAndDeletedFlagFalse(Long channelId);
}
