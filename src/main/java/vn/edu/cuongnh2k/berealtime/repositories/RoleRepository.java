package vn.edu.cuongnh2k.berealtime.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import vn.edu.cuongnh2k.berealtime.entities.RoleEntity;
import vn.edu.cuongnh2k.berealtime.enums.RoleEnum;

import java.util.List;

@Repository
public interface RoleRepository extends JpaRepository<RoleEntity, Long> {

    List<RoleEntity> findByName(RoleEnum roleEnum);
}
