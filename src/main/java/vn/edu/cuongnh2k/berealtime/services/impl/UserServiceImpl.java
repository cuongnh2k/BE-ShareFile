package vn.edu.cuongnh2k.berealtime.services.impl;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;
import vn.edu.cuongnh2k.berealtime.basess.BaseListProduceDto;
import vn.edu.cuongnh2k.berealtime.dto.consumes.UserConsumeDto;
import vn.edu.cuongnh2k.berealtime.dto.produces.UserProduceDto;
import vn.edu.cuongnh2k.berealtime.entities.UserEntity;
import vn.edu.cuongnh2k.berealtime.enums.RoleEnum;
import vn.edu.cuongnh2k.berealtime.exceptions.BadRequestException;
import vn.edu.cuongnh2k.berealtime.mapper.UserMapper;
import vn.edu.cuongnh2k.berealtime.repositories.RoleRepository;
import vn.edu.cuongnh2k.berealtime.repositories.UserRepository;
import vn.edu.cuongnh2k.berealtime.services.UserService;
import vn.edu.cuongnh2k.berealtime.utils.ConvertUtil;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Objects;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import static org.springframework.http.HttpHeaders.AUTHORIZATION;

@Service
@Transactional
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository mUserRepository;
    private final RoleRepository mRoleRepository;
    private final PasswordEncoder mPasswordEncoder;
    private final HttpServletRequest mHttpServletRequest;
    private final UserMapper mUserMapper;

    @Value("${jwt_secret}")
    private String JWT_SECRET;

    @Value("${directory}")
    private String DIRECTORY;

    @Value("${subfolder_avatar}")
    private String SUBFOLDER_AVATAR;

    @Value("${domain}")
    private String DOMAIN;

    public void createAdmin(UserEntity userEntity) {
        userEntity.setPassword(mPasswordEncoder.encode(userEntity.getPassword()));
        userEntity.setRoles(mRoleRepository.findAll());
        mUserRepository.save(userEntity);
    }

    @Override
    public String getEmail() {
        String authorizationHeader = mHttpServletRequest.getHeader(AUTHORIZATION);
        String refreshToken = authorizationHeader.substring("Bearer ".length());
        Algorithm algorithm = Algorithm.HMAC256(JWT_SECRET.getBytes());
        JWTVerifier verifier = JWT.require(algorithm).build();
        DecodedJWT decodedJWT = verifier.verify(refreshToken);
        return decodedJWT.getSubject();
    }

    @Override
    public UserEntity getCurrentUser() {
        return mUserRepository.findByEmail(getEmail());
    }

    @Override
    public UserProduceDto getAccount() {
        return mUserMapper.toUserProduceDto(getCurrentUser());
    }

    @Override
    public UserProduceDto register(UserConsumeDto userConsumeDto) {
        UserEntity userEntity = userConsumeDto.toUserEntity();
        if (mUserRepository.existsByEmail(userEntity.getEmail())) {
            throw new BadRequestException("email already in use");
        }
        userEntity.setPassword(mPasswordEncoder.encode(userEntity.getPassword()));
        userEntity.setRoles(mRoleRepository.findByName(RoleEnum.ROLE_USER));
        return mUserMapper.toUserProduceDto(mUserRepository.save(userEntity));
    }

    @Override
    public UserProduceDto editUser(UserConsumeDto userConsumeDto) {
        UserEntity userEntity = getCurrentUser();
        if (Objects.nonNull(userConsumeDto.getPassword())) {
            userEntity.setPassword(mPasswordEncoder.encode(userConsumeDto.getPassword()));
        }
        if (Objects.nonNull(userConsumeDto.getFirstName())) {
            userEntity.setFirstName(userConsumeDto.getFirstName());
        }
        if (Objects.nonNull(userConsumeDto.getLastName())) {
            userEntity.setLastName(userConsumeDto.getLastName());
        }
        return mUserMapper.toUserProduceDto(mUserRepository.save(userEntity));
    }

    @Override
    public UserProduceDto addAvatar(MultipartFile multipartFile) throws IOException {
        String fileName = StringUtils.cleanPath(Objects.requireNonNull(multipartFile.getOriginalFilename()));
        if (!fileName.contains(".")) {
            throw new BadRequestException("avatar not selected");
        }
        String[] arr = fileName.split("\\.");
        if (!arr[1].equalsIgnoreCase("JPG") && !arr[1].equalsIgnoreCase("PNG")) {
            throw new BadRequestException("avatar must be in jpg or png format");
        }
        if (!Pattern.matches("^\\w+$", arr[0])) {
            throw new BadRequestException("file name not contains special character");
        }
        UserEntity userEntity = getCurrentUser();
        if (Objects.nonNull(userEntity.getAvatar()) &&
                userEntity.getAvatar().equals(DOMAIN + SUBFOLDER_AVATAR + "/" + userEntity.getId() + "/" + fileName)) {
            throw new BadRequestException("avatar already exists");
        }
        Path uploadPath = Paths.get(DIRECTORY + SUBFOLDER_AVATAR + "/" + userEntity.getId());
        if (!Files.exists(uploadPath)) {
            Files.createDirectories(uploadPath);
        }
        try (InputStream inputStream = multipartFile.getInputStream()) {
            Path filePath = uploadPath.resolve(fileName);
            Files.copy(inputStream, filePath, StandardCopyOption.REPLACE_EXISTING);
        } catch (Exception ioe) {
            throw new BadRequestException(ioe.getMessage());
        }
        try {
            String[] name = userEntity.getAvatar().split("/");
            File file = new File(DIRECTORY + SUBFOLDER_AVATAR + "/" + userEntity.getId() + "/" + name[name.length - 1]);
            file.delete();
        } catch (Exception ignored) {
        }
        userEntity.setAvatar(DOMAIN + SUBFOLDER_AVATAR + "/" + userEntity.getId() + "/" + fileName);
        return mUserMapper.toUserProduceDto(mUserRepository.save(userEntity));
    }

    @Override
    public BaseListProduceDto<UserProduceDto> searchUserByEmail(Integer page, Integer size, String sort, String email) {
        Page<UserEntity> userEntityPage = mUserRepository.findByEmailContaining(
                email,
                ConvertUtil.buildPageable(page, size, sort));
        return BaseListProduceDto.<UserProduceDto>builder()
                .content(userEntityPage.getContent().stream()
                        .map(mUserMapper::toUserProduceDto)
                        .collect(Collectors.toList()))
                .totalElements(userEntityPage.getTotalElements())
                .totalPages(userEntityPage.getTotalPages())
                .page(page)
                .size(size)
                .build();
    }
}