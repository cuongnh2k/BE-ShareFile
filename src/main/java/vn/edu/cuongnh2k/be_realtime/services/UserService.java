package vn.edu.cuongnh2k.be_realtime.services;

import org.springframework.web.multipart.MultipartFile;
import vn.edu.cuongnh2k.be_realtime.basess.BaseListProduceDto;
import vn.edu.cuongnh2k.be_realtime.dto.consumes.UserConsumeDto;
import vn.edu.cuongnh2k.be_realtime.dto.produces.UserProduceDto;
import vn.edu.cuongnh2k.be_realtime.entities.UserEntity;

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
