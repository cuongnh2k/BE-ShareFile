package vn.edu.cuongnh2k.be_realtime.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import vn.edu.cuongnh2k.be_realtime.basess.BaseEntity;
import vn.edu.cuongnh2k.be_realtime.enums.RoleEnum;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "role")
public class RoleEntity extends BaseEntity {

    @Enumerated(EnumType.STRING)
    @Column(name = "name", nullable = false, unique = true)
    private RoleEnum name;

    @ManyToMany(mappedBy = "roles")
    private List<UserEntity> users;
}
