package vn.edu.cuongnh2k.be_realtime.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import vn.edu.cuongnh2k.be_realtime.entities.UserChannelEntity;

@Repository
public interface UserChannelRepository extends JpaRepository<UserChannelEntity, Long> {
}
