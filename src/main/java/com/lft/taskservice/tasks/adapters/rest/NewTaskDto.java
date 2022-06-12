package com.lft.taskservice.tasks.adapters.rest;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class NewTaskDto {

    private String name;
    private String description;
    private String programmingLang;
    private String programmingLangVersion;

}
