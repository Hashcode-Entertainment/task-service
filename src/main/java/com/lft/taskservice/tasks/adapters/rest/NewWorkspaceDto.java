package com.lft.taskservice.tasks.adapters.rest;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class NewWorkspaceDto {

    private String owner;
    private String template;

}
