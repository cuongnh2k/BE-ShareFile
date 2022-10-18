package com.example.besharefile.entities;

import com.example.besharefile.basess.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;
import java.util.Collection;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
@Entity
@Table(name = "users")
public class UsersEntity extends BaseEntity {

    @Column(unique = true)
    private String email;

    @Column(columnDefinition = "text")
    private String password;

    private String firstName;

    private String lastName;

    @ManyToMany
    @JoinTable(
            name = "users_roles",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    private List<RolesEntity> roles;

    @ManyToMany
    @JoinTable(
            name = "users_organizations",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "organization_id"))
    private List<OrganizationsEntity> organizations;

    @OneToMany(mappedBy = "user")
    private Collection<DevicesEntity> devices;

    @OneToMany(mappedBy = "user")
    private Collection<FoldersEntity> folders;

    @OneToMany(mappedBy = "user")
    private Collection<FolderAccessEntity> foldersAccess;

}