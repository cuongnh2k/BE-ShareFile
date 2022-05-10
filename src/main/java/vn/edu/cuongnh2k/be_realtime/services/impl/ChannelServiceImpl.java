package vn.edu.cuongnh2k.be_realtime.services.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import vn.edu.cuongnh2k.be_realtime.basess.BaseListProduceDto;
import vn.edu.cuongnh2k.be_realtime.dto.produces.ChannelProduceDto;
import vn.edu.cuongnh2k.be_realtime.dto.produces.UserChannelProduceDto;
import vn.edu.cuongnh2k.be_realtime.entities.ChannelEntity;
import vn.edu.cuongnh2k.be_realtime.mapper.ChannelMapper;
import vn.edu.cuongnh2k.be_realtime.mapper.UserChannelMapper;
import vn.edu.cuongnh2k.be_realtime.mapper.UserMapper;
import vn.edu.cuongnh2k.be_realtime.repositories.ChannelRepository;
import vn.edu.cuongnh2k.be_realtime.services.ChannelService;
import vn.edu.cuongnh2k.be_realtime.services.UserService;
import vn.edu.cuongnh2k.be_realtime.utils.ConvertUtil;

import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class ChannelServiceImpl implements ChannelService {

    private final ChannelRepository mChannelRepository;
    private final UserService mUserService;
    private final ChannelMapper mChannelMapper;
    private final UserChannelMapper mUserChannelMapper;
    private final UserMapper mUserMapper;

    @Override
    public BaseListProduceDto<ChannelProduceDto> getChannelByUser(Integer page, Integer size, String sort) {
        Page<ChannelEntity> channelEntityPage = mChannelRepository.getChannelByUser(
                mUserService.getCurrentUser().getId(),
                ConvertUtil.buildPageable(page, size, sort));
        return BaseListProduceDto.<ChannelProduceDto>builder()
                .content(channelEntityPage.getContent().stream()
                        .map(o -> {
                            ChannelProduceDto channelProduceDto = mChannelMapper.toChannelProduceDto(o);
                            channelProduceDto.setUserChannels(o.getUserChannels().stream()
                                    .map(oo -> {
                                        UserChannelProduceDto userChannelProduceDto = mUserChannelMapper.toUserChannelProduceDto(oo);
                                        userChannelProduceDto.setUser(mUserMapper.toUserProduceDto(oo.getUser()));
                                        return userChannelProduceDto;
                                    })
                                    .collect(Collectors.toList()));
                            return channelProduceDto;
                        })
                        .collect(Collectors.toList()))
                .totalElements(channelEntityPage.getTotalElements())
                .totalPages(channelEntityPage.getTotalPages())
                .page(page)
                .size(size)
                .build();
    }
}
