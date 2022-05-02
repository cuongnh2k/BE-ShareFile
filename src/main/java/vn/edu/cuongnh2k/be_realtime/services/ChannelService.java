package vn.edu.cuongnh2k.be_realtime.services;

import vn.edu.cuongnh2k.be_realtime.basess.BaseListProduceDto;
import vn.edu.cuongnh2k.be_realtime.dto.produces.ChannelProduceDto;

public interface ChannelService {

    BaseListProduceDto<ChannelProduceDto> getChannelByUser(Integer page, Integer size, String sort);
}
