package vn.edu.cuongnh2k.berealtime.services.impl;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import vn.edu.cuongnh2k.berealtime.basess.BaseListProduceDto;
import vn.edu.cuongnh2k.berealtime.dto.produces.DeviceProduceDto;
import vn.edu.cuongnh2k.berealtime.dto.produces.TokenProduceDto;
import vn.edu.cuongnh2k.berealtime.entities.DeviceEntity;
import vn.edu.cuongnh2k.berealtime.entities.UserEntity;
import vn.edu.cuongnh2k.berealtime.exceptions.UnauthorizedException;
import vn.edu.cuongnh2k.berealtime.mapper.DeviceMapper;
import vn.edu.cuongnh2k.berealtime.repositories.DeviceRepository;
import vn.edu.cuongnh2k.berealtime.repositories.UserRepository;
import vn.edu.cuongnh2k.berealtime.services.DeviceService;
import vn.edu.cuongnh2k.berealtime.services.UserService;
import vn.edu.cuongnh2k.berealtime.utils.ConvertUtil;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import static org.springframework.http.HttpHeaders.AUTHORIZATION;
import static org.springframework.http.HttpHeaders.USER_AGENT;

@Service
@Transactional
@RequiredArgsConstructor
public class DeviceServiceImpl implements DeviceService {

    private final UserRepository mUserRepository;
    private final DeviceRepository mDeviceRepository;
    private final UserService mUserService;
    private final DeviceMapper mDeviceMapper;

    @Value("${jwt_secret}")
    private String JWT_SECRET;

    @Value("${jwt_access_token_validity_period}")
    private Long JWT_ACCESS_TOKEN_VALIDITY_PERIOD;

    @Override
    public void updateToken(HttpServletRequest httpServletRequest, TokenProduceDto tokenProduceDto, String email) {
        UserEntity userEntity = mUserRepository.findByEmail(email);
        DeviceEntity deviceEntity = mDeviceRepository.findByUserAgentAndUserId(
                httpServletRequest.getHeader(USER_AGENT),
                userEntity.getId());
        if (Objects.nonNull(deviceEntity)) {
            deviceEntity.setAccessToken(tokenProduceDto.getAccessToken());
            deviceEntity.setRefreshToken(tokenProduceDto.getRefreshToken());
        } else {
            deviceEntity = DeviceEntity.builder()
                    .userAgent(httpServletRequest.getHeader(USER_AGENT))
                    .accessToken(tokenProduceDto.getAccessToken())
                    .refreshToken(tokenProduceDto.getRefreshToken())
                    .user(userEntity)
                    .build();
        }
        mDeviceRepository.save(deviceEntity);
    }

    @Override
    public TokenProduceDto refreshToken(HttpServletRequest request) {
        String authorizationHeader = request.getHeader(AUTHORIZATION);
        if (Objects.nonNull(authorizationHeader) && authorizationHeader.startsWith("Bearer ")) {
            try {
                String refreshToken = authorizationHeader.substring("Bearer ".length());
                Algorithm algorithm = Algorithm.HMAC256(JWT_SECRET.getBytes());
                JWTVerifier verifier = JWT.require(algorithm).build();
                DecodedJWT decodedJWT = verifier.verify(refreshToken);
                if (decodedJWT.getClaim("type").asString().equalsIgnoreCase("refresh")) {
                    UserEntity userEntity = mUserRepository.findByEmail(decodedJWT.getSubject());
                    TokenProduceDto tokenProduceDto = TokenProduceDto.builder()
                            .accessToken(JWT.create()
                                    .withSubject(userEntity.getEmail())
                                    .withExpiresAt(new Date(System.currentTimeMillis() + JWT_ACCESS_TOKEN_VALIDITY_PERIOD))
                                    .withIssuer(request.getRequestURL().toString())
                                    .withClaim(
                                            "roles",
                                            userEntity.getRoles().stream()
                                                    .map(o -> o.getName().name())
                                                    .collect(Collectors.toList()))
                                    .withClaim("type", "access")
                                    .sign(algorithm))
                            .refreshToken(refreshToken)
                            .build();
                    updateAccessToken(request, userEntity.getId(), tokenProduceDto.getAccessToken());
                    return tokenProduceDto;
                } else {
                    throw new UnauthorizedException("unauthorized");
                }
            } catch (Exception exception) {
                throw new UnauthorizedException(exception.getMessage());
            }
        } else {
            throw new UnauthorizedException("unauthorized");
        }
    }

    public void updateAccessToken(HttpServletRequest request, Long userId, String accessToken) {
        DeviceEntity deviceEntity = mDeviceRepository.findByUserAgentAndUserId(request.getHeader(USER_AGENT), userId);
        if (Objects.nonNull(deviceEntity)) {
            deviceEntity.setAccessToken(accessToken);
            mDeviceRepository.save(deviceEntity);
        } else {
            throw new UnauthorizedException("expired version");
        }
    }

    @Override
    public void logout(HttpServletRequest httpServletRequest) {
        DeviceEntity deviceEntity = mDeviceRepository.findByUserAgentAndUserId(
                httpServletRequest.getHeader(USER_AGENT)
                , mUserService.getCurrentUser().getId());
        if (Objects.isNull(deviceEntity)) {
            throw new UnauthorizedException("expired version");
        }
        mDeviceRepository.delete(deviceEntity);
    }

    @Override
    public void logouts(Long[] deviceIds) {
        mDeviceRepository.deleteAllByIdInAndUserId(List.of(deviceIds), mUserService.getCurrentUser().getId());
    }

    @Override
    public BaseListProduceDto<DeviceProduceDto> getDeviceByUser(Integer page, Integer size, String sort) {
        Page<DeviceEntity> deviceEntityPage = mDeviceRepository.findByUserId(
                mUserService.getCurrentUser().getId(),
                ConvertUtil.buildPageable(page, size, sort));
        return BaseListProduceDto.<DeviceProduceDto>builder()
                .content(deviceEntityPage.getContent().stream()
                        .map(mDeviceMapper::toDeviceProduceDto)
                        .collect(Collectors.toList()))
                .totalElements(deviceEntityPage.getTotalElements())
                .totalPages(deviceEntityPage.getTotalPages())
                .page(page)
                .size(size)
                .build();
    }
}
