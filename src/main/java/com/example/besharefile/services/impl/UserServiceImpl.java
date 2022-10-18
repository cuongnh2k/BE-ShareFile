package com.example.besharefile.services.impl;

import com.example.besharefile.entities.UsersEntity;
import com.example.besharefile.repositories.RoleRepository;
import com.example.besharefile.repositories.UserRepository;
import com.example.besharefile.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository mUserRepository;
    private final RoleRepository mRoleRepository;
    private final PasswordEncoder mPasswordEncoder;

    public void createOneAdmin(UsersEntity entity) {
        entity.setPassword(mPasswordEncoder.encode(entity.getPassword()));
        entity.setRoles(mRoleRepository.findAll());
        mUserRepository.save(entity);
    }
}