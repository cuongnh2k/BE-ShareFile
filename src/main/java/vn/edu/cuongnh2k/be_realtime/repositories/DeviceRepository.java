package vn.edu.cuongnh2k.be_realtime.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import vn.edu.cuongnh2k.be_realtime.entities.DeviceEntity;

import java.util.List;

@Repository
public interface DeviceRepository extends JpaRepository<DeviceEntity, Long> {

    DeviceEntity findByUserAgentAndUserId(String userAgent, Long userId);

    Boolean existsByUserAgentAndAccessToken(String userAgent, String accessToken);

    Page<DeviceEntity> findByUserId(Long userId, Pageable pageable);

    void deleteAllByIdInAndUserId(List<Long> ids, Long userId);
}
