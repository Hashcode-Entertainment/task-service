package com.lft.taskservice.tasks.adapters.persistance;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Table(name = "TASKS")
@ToString
@Getter
@Setter
public class TaskEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String ownerEmail;
    private String name;
    private String description;
    private String programmingLang;
    private String programmingLangVersion;
    private UUID workspaceId;
    private String workspaceUrl;

}
