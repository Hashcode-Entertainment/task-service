package com.lft.taskservice.tasks.adapters.persistance;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalDate;

@Entity
@Table(name = "ASSIGNMENTS")
@ToString
@Getter
@Setter
public class AssignmentEntity {

    @Id
    @GeneratedValue
    private Long id;
    private Long taskId;
    private Long userId;
    private LocalDate deadline;
    private LocalDate assignedOn;

}