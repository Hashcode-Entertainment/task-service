package com.lft.taskservice.tasks.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class Task {

    private Long id;
    private String ownerEmail;
    private String name;
    private String description;
    private String programmingLang;
    private String programmingLangVersion;
    private UUID workspaceId;
    private String workspaceUrl;

}
