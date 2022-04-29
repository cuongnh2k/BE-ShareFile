package vn.edu.cuongnh2k.be_realtime.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import vn.edu.cuongnh2k.be_realtime.entities.UserEntity;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {

    UserEntity findByEmail(String email);

    Boolean existsByEmail(String email);

    Page<UserEntity> findByEmailContaining(String email, Pageable pageable);
}
