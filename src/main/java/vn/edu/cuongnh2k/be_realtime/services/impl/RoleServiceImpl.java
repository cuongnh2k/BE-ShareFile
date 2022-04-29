package vn.edu.cuongnh2k.be_realtime.services.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import vn.edu.cuongnh2k.be_realtime.entities.RoleEntity;
import vn.edu.cuongnh2k.be_realtime.repositories.RoleRepository;
import vn.edu.cuongnh2k.be_realtime.services.RoleService;

@Service
@Transactional
@RequiredArgsConstructor
public class RoleServiceImpl implements RoleService {

    private final RoleRepository mRoleRepository;

    public void create(RoleEntity roleEntity) {
        mRoleRepository.save(roleEntity);
    }
}
