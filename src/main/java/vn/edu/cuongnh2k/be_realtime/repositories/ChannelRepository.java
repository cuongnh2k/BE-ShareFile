package vn.edu.cuongnh2k.be_realtime.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import vn.edu.cuongnh2k.be_realtime.entities.ChannelEntity;

@Repository
public interface ChannelRepository extends JpaRepository<ChannelEntity, Long> {

    @Query(nativeQuery = true,
            value = "SELECT * " +
                    "FROM `channel` " +
                    "WHERE `channel`.id " +
                    "IN ( " +
                    "   SELECT `channel`.id " +
                    "   FROM `channel` " +
                    "   INNER JOIN user_channel " +
                    "   ON `channel`.id=user_channel.channel_id " +
                    "   WHERE `channel`.deleted_flag=0 AND user_channel.user_id=?1 " +
                    "   GROUP BY `channel`.id) ")
    Page<ChannelEntity> getChannelByUser(Long userId, Pageable pageable);
}
