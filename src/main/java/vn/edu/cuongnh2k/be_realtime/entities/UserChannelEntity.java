package vn.edu.cuongnh2k.be_realtime.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import vn.edu.cuongnh2k.be_realtime.basess.BaseEntity;

import javax.persistence.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
@Entity
@Table(name = "user_channel")
public class UserChannelEntity extends BaseEntity {

    @Column(name = "name", nullable = false)
    private String name;

    @ManyToOne
    @JoinColumn(columnDefinition = "user_id")
    private UserEntity user;

    @ManyToOne
    @JoinColumn(columnDefinition = "channel_id")
    private ChannelEntity channel;
}