package com.lft.taskservice.tasks.adapters.persistance;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

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
    private String workspaceUrl;


}
