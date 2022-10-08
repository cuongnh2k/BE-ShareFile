package com.example.besharefile.entities;

import com.example.besharefile.basess.BaseEntity;
import com.example.besharefile.enums.RoleEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "roles")
public class RoleEntity extends BaseEntity {

    @Enumerated(EnumType.STRING)
    @Column(unique = true)
    private RoleEnum name;

    @ManyToMany(mappedBy = "roles")
    private List<UserEntity> users;
}
