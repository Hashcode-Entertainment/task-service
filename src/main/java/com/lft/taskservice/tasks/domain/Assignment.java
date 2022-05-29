package com.lft.taskservice.tasks.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class Assignment {

    private Long taskId;
    private Long userId;
    private LocalDate deadline;
    private LocalDate assignedOn = LocalDate.now();

}
