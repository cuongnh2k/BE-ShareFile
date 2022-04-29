package vn.edu.cuongnh2k.be_realtime.services;

import vn.edu.cuongnh2k.be_realtime.basess.BaseListProduceDto;
import vn.edu.cuongnh2k.be_realtime.dto.produces.DeviceProduceDto;
import vn.edu.cuongnh2k.be_realtime.dto.produces.TokenProduceDto;

import javax.servlet.http.HttpServletRequest;

public interface DeviceService {

    void updateToken(HttpServletRequest httpServletRequest, TokenProduceDto tokenProduceDto, String email);

    TokenProduceDto refreshToken(HttpServletRequest httpServletRequest);

    void logout(HttpServletRequest request);

    void logouts(Long[] deviceIds);

    BaseListProduceDto<DeviceProduceDto> getDeviceByUser(Integer page, Integer size, String sort);
}
