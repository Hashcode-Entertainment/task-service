package com.lft.taskservice.tasks.adapters.persistance;

import com.lft.taskservice.tasks.adapters.logging.TaskLogging;
import com.lft.taskservice.tasks.domain.Task;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

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

    @TaskLogging
    <S, T> List<T> mapList(List<S> source, Class<T> targetClass) {
        return source
                .stream()
                .map(element -> mapper.map(element, targetClass))
                .collect(Collectors.toList());
    }

}
