package vn.edu.cuongnh2k.be_realtime.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import vn.edu.cuongnh2k.be_realtime.entities.RoleEntity;
import vn.edu.cuongnh2k.be_realtime.enums.RoleEnum;

import java.util.List;

@Repository
public interface RoleRepository extends JpaRepository<RoleEntity, Long> {

    List<RoleEntity> findByName(RoleEnum roleEnum);
}
