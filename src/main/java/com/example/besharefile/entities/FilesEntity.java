package com.example.besharefile.entities;

import com.example.besharefile.basess.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;
import java.util.Collection;

@Entity
@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "files")
public class FilesEntity extends BaseEntity {

    private String name;

    private String path;

    @ManyToOne
    @JoinColumn(name = "folder_id")
    private FoldersEntity files;

    @OneToMany(mappedBy = "files")
    private Collection<FolderAccessEntity> folderAccess;
}
