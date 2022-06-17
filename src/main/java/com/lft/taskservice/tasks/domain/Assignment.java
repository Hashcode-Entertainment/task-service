package com.lft.taskservice.tasks.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class Assignment {

    private Long id;
    private Task task;
    private String userWorkspaceUrl;
    private Long userId;
    private LocalDateTime deadline;
    private LocalDateTime assignedOn = LocalDateTime.now();

}
