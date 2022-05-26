package com.lft.taskservice.tasks.adapters.rest;

import lombok.Data;

@Data
public class NewTaskDto {

    private String name;
    private String description;
    private String programmingLang;
    private String programmingLangVersion;

}
