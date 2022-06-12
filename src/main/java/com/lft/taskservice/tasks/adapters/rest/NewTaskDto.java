package com.lft.taskservice.tasks.adapters.rest;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class NewTaskDto {

    private String name;
    private String description;
    private String programmingLang;
    private String programmingLangVersion;

}
