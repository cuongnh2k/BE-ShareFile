package vn.edu.cuongnh2k.berealtime.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import vn.edu.cuongnh2k.berealtime.entities.ChannelEntity;

@Repository
public interface ChannelRepository extends JpaRepository<ChannelEntity, Long> {
}
