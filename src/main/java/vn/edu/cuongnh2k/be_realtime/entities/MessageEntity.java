package vn.edu.cuongnh2k.be_realtime.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import vn.edu.cuongnh2k.be_realtime.basess.BaseEntity;
import vn.edu.cuongnh2k.be_realtime.enums.MessageEnum;

import javax.persistence.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
@Entity
@Table(name = "message")
public class MessageEntity extends BaseEntity {

    @Column(name = "content", columnDefinition = "text")
    private String content;

    @Enumerated(EnumType.STRING)
    @Column(name = "type", nullable = false)
    private MessageEnum type;

    @ManyToOne
    @JoinColumn(columnDefinition = "user_id")
    private UserEntity user;

    @ManyToOne
    @JoinColumn(columnDefinition = "channel_id")
    private ChannelEntity channel;
}