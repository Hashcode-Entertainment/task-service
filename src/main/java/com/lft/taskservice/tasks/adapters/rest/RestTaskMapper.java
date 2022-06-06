package com.lft.taskservice.tasks.adapters.rest;

import com.lft.taskservice.tasks.adapters.logging.TaskLogging;
import com.lft.taskservice.tasks.domain.Task;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class RestTaskMapper {

    private final ModelMapper mapper = new ModelMapper();

    @TaskLogging
    Task toDomain(NewTaskDto newTaskDto) { return mapper.map(newTaskDto, Task.class);
    }

    @TaskLogging
    TaskDto toDto(Task task) {
        return mapper.map(task, TaskDto.class);
    }

    @TaskLogging
    <S, T> List<T> mapList(List<S> source, Class<T> targetClass) {
        return source
                .stream()
                .map(element -> mapper.map(element, targetClass))
                .collect(Collectors.toList());
    }


}
