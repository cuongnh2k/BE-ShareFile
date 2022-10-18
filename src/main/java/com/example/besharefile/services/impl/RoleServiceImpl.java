package com.example.besharefile.services.impl;

import com.example.besharefile.entities.RolesEntity;
import com.example.besharefile.repositories.RoleRepository;
import com.example.besharefile.services.RoleService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class RoleServiceImpl implements RoleService {

    private final RoleRepository mRoleRepository;

    public void createOne(RolesEntity entity) {
        mRoleRepository.save(entity);
    }
}
