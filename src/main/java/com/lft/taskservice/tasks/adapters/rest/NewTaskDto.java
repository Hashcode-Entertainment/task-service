package com.lft.taskservice.tasks.adapters.rest;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

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
