package vn.edu.cuongnh2k.be_realtime.dto.produces;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import vn.edu.cuongnh2k.be_realtime.basess.BaseProduceDto;
import vn.edu.cuongnh2k.be_realtime.enums.RoleEnum;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public class RoleProduceDto extends BaseProduceDto<Long> {

    private RoleEnum name;

    private List<UserProduceDto> users;
}
