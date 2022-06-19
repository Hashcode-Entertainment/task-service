package com.lft.taskservice.tasks.adapters.rest;

import lombok.Data;

import java.util.UUID;

@Data
public class TaskDto {

    private Long id;
    private String ownerEmail;
    private String name;
    private String description;
    private String programmingLang;
    private String programmingLangVersion;
    private UUID workspaceId;
    private String workspaceUrl;

}
