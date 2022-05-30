package com.lft.taskservice.tasks.adapters.persistance;

import com.lft.taskservice.tasks.adapters.logging.TaskLogging;
import com.lft.taskservice.tasks.domain.Assignment;
import com.lft.taskservice.tasks.domain.Task;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class JpaAssignmentMapper {

    private final ModelMapper mapper = new ModelMapper();

    @TaskLogging
    Assignment toDomain(AssignmentEntity assignmentEntity) {
        return mapper.map(assignmentEntity, Assignment.class);
    }

    @TaskLogging
    AssignmentEntity toEntity(Assignment assignment) {
        return mapper.map(assignment, AssignmentEntity.class);
    }

}
