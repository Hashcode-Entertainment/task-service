package com.lft.taskservice.tasks.adapters.rest;

import lombok.Data;

@Data
public class TaskDto {

    private Long id;
    private String name;
    private String description;
    private String programmingLang;
    private String programmingLangVersion;
    private String workspaceUrl;

}
