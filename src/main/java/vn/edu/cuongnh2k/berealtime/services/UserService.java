package vn.edu.cuongnh2k.berealtime.services;

import org.springframework.web.multipart.MultipartFile;
import vn.edu.cuongnh2k.berealtime.basess.BaseListProduceDto;
import vn.edu.cuongnh2k.berealtime.dto.consumes.UserConsumeDto;
import vn.edu.cuongnh2k.berealtime.dto.produces.UserProduceDto;
import vn.edu.cuongnh2k.berealtime.entities.UserEntity;

import java.io.IOException;

public interface UserService {

    String getEmail();

    UserEntity getCurrentUser();

    UserProduceDto getAccount();

    UserProduceDto register(UserConsumeDto userConsumeDto);

    UserProduceDto editUser(UserConsumeDto userConsumeDto);

    UserProduceDto addAvatar(MultipartFile multipartFile) throws IOException;

    BaseListProduceDto<UserProduceDto> searchUserByEmail(Integer page, Integer size, String sort, String email);
}
