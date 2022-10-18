package com.example.besharefile.entities;

import com.example.besharefile.basess.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.Collection;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
@Entity
@Table(name = "organizations")
public class OrganizationsEntity extends BaseEntity {

    private String name;

    @ManyToMany(mappedBy = "organizations")
    private List<UsersEntity> users;

    @OneToMany(mappedBy = "organizations")
    private Collection<FolderAccessEntity> folderAccess;

}
