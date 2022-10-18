package com.example.besharefile.config;

import com.example.besharefile.entities.UsersEntity;
import com.example.besharefile.exceptions.BadRequestException;
import com.example.besharefile.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserDetailServiceConfig implements UserDetailsService {

    private final UserRepository mUserRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        UsersEntity userEntity = mUserRepository.findByEmail(email);
        if (Objects.isNull(userEntity)) {
            throw new BadRequestException(email + " not found in database");
        } else {
            Collection<SimpleGrantedAuthority> authorities = userEntity.getRoles()
                    .stream()
                    .map(roleEntity -> new SimpleGrantedAuthority(roleEntity.getName().toString()))
                    .collect(Collectors.toList());
            return new User(userEntity.getEmail(), userEntity.getPassword(), authorities);
        }
    }
}
