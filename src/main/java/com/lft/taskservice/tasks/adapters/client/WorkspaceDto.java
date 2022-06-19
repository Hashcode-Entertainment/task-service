package com.lft.taskservice.tasks.adapters.client;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class WorkspaceDto {

    private UUID id;
    private String owner;
    private String template;
    private String url;

}
