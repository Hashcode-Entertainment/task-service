package com.lft.taskservice.tasks.adapters.rest;

import lombok.Data;

import java.time.LocalDate;

@Data
public class AssignmentDto {

    private Long id;
    private TaskDto task;
    private Long userId;
    private LocalDate deadline;
    private LocalDate assignedOn;

}
