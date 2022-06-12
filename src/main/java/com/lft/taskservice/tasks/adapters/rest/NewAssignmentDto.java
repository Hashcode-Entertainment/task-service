package com.lft.taskservice.tasks.adapters.rest;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

@Data
@Builder
public class NewAssignmentDto {

    private Long taskId;
    private Long userId;
    private LocalDate deadline;

}
