package vn.edu.cuongnh2k.berealtime.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import vn.edu.cuongnh2k.berealtime.entities.UserChannelEntity;

@Repository
public interface UserChannelRepository extends JpaRepository<UserChannelEntity, Long> {
}
