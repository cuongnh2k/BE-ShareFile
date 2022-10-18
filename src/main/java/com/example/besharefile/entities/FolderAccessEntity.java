package com.example.besharefile.entities;

import com.example.besharefile.basess.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "folder_access")
public class FolderAccessEntity extends BaseEntity {

    private String section;

    @ManyToOne
    @JoinColumn(name = "organization_id")
    private OrganizationsEntity organizations;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private UsersEntity user;

    @ManyToOne
    @JoinColumn(name = "folder_id")
    private FoldersEntity folders;

    @ManyToOne
    @JoinColumn(name = "file_id")
    private FilesEntity files;

}
