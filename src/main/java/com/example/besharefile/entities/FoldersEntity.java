package com.example.besharefile.entities;

import com.example.besharefile.basess.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;
import java.util.Collection;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
@Entity
@Table(name = "folders")
public class FoldersEntity extends BaseEntity {

    private String accessModifier;

    private String parentId;

    private String name;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private UsersEntity user;

    @OneToMany(mappedBy = "folders")
    private Collection<FolderHistoriesEntity> folderHistories;

    @OneToMany(mappedBy = "files")
    private Collection<FilesEntity> files;

    @OneToMany(mappedBy = "folders")
    private Collection<FolderAccessEntity> folderAccess;
}
