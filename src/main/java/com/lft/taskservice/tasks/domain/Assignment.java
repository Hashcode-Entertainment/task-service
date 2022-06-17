package com.lft.taskservice.tasks.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class Assignment {

    private Long id;
    private Task task;
    private String workspaceUrl;
    private Long userId;
    private LocalDate deadline;
    private LocalDate assignedOn = LocalDate.now();

}
