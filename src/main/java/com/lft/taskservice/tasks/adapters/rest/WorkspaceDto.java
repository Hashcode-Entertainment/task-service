package com.lft.taskservice.tasks.adapters.rest;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.UUID;

@Getter
@AllArgsConstructor
public class WorkspaceDto {

    private UUID id;
    private String owner;
    private String template;
    private String url;

}
