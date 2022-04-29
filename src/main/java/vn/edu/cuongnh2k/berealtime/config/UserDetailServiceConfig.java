package vn.edu.cuongnh2k.berealtime.config;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import vn.edu.cuongnh2k.berealtime.entities.UserEntity;
import vn.edu.cuongnh2k.berealtime.exceptions.BadRequestException;
import vn.edu.cuongnh2k.berealtime.repositories.UserRepository;

import java.util.Collection;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class UserDetailServiceConfig implements UserDetailsService {

    private final UserRepository mUserRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        UserEntity userEntity = mUserRepository.findByEmail(email);
        if (Objects.isNull(userEntity)) {
            throw new BadRequestException(email + " not found in database");
        } else {
            Collection<SimpleGrantedAuthority> authorities = userEntity.getRoles().stream().map(roleEntity ->
                    new SimpleGrantedAuthority(roleEntity.getName().toString())).collect(Collectors.toList());
            return new User(userEntity.getEmail(), userEntity.getPassword(), authorities);
        }
    }
}
