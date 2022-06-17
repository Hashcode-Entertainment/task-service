package com.lft.taskservice.tasks.adapters.persistance;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "ASSIGNMENTS")
@ToString
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AssignmentEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @OneToOne
    private TaskEntity task;
    private Long userId;
    private String userWorkspaceUrl;
    private LocalDateTime deadline;
    private LocalDateTime assignedOn;

}
