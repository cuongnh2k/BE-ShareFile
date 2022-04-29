package vn.edu.cuongnh2k.berealtime.dto.produces;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import vn.edu.cuongnh2k.berealtime.basess.BaseProduceDto;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
public class DeviceProduceDto extends BaseProduceDto<Long> {

    private String userAgent;

    private UserProduceDto user;
}
