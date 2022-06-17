package com.lft.taskservice.tasks.adapters.rest;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class AssignmentDto {

    private Long id;
    private TaskDto task;
    private String userWorkspaceUrl;
    private Long userId;
    private LocalDateTime deadline;
    private LocalDateTime assignedOn;

}
