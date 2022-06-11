package com.lft.taskservice.tasks.domain;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class Task {

    private Long id;
    private String name;
    private String description;
    private String programmingLang;
    private String programmingLangVersion;
    private String workspaceUrl;

}
