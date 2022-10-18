package com.example.besharefile.entities;

import com.example.besharefile.basess.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;

@Entity
@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "devices")
public class DevicesEntity extends BaseEntity {

    @Column(nullable = false, columnDefinition = "text")
    private String userAgent;

    @Column(nullable = false, columnDefinition = "text")
    private String accessToken;

    @Column(nullable = false, columnDefinition = "text")
    private String refreshToken;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private UsersEntity user;
}
