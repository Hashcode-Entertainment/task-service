package com.lft.taskservice.tasks.adapters.persistance;

import com.lft.taskservice.tasks.adapters.logging.TaskLogging;
import com.lft.taskservice.tasks.domain.Task;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class JpaTaskMapper {

    private final ModelMapper mapper = new ModelMapper();

    @TaskLogging
    Task toDomain(TaskEntity taskEntity) {
        return mapper.map(taskEntity, Task.class);
    }

    @TaskLogging
    TaskEntity toEntity(Task task) {
        return mapper.map(task, TaskEntity.class);
    }

}
