package vn.edu.cuongnh2k.be_realtime.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import vn.edu.cuongnh2k.be_realtime.basess.BaseEntity;
import vn.edu.cuongnh2k.be_realtime.enums.ChannelEnum;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
@Entity
@Table(name = "channel")
public class ChannelEntity extends BaseEntity {

    @Column(name = "name", nullable = false)
    private String name;

    @Enumerated(EnumType.STRING)
    @Column(name = "type", nullable = false)
    private ChannelEnum type;

    @OneToMany(mappedBy = "channel")
    private List<UserChannelEntity> userChannels;

    @OneToMany(mappedBy = "channel")
    private List<MessageEntity> messages;
}