package com.example.besharefile.entities;

import com.example.besharefile.basess.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
@Entity
@Table(name = "folder_histories")
public class FolderHistoriesEntity extends BaseEntity {

    private String note;

    @ManyToOne
    @JoinColumn(name = "folder_id")
    private FoldersEntity folders;
}
