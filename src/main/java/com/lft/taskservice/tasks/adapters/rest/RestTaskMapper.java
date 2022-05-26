package com.lft.taskservice.tasks.adapters.rest;

import com.lft.taskservice.tasks.domain.Task;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class RestTaskMapper {

    private final ModelMapper mapper = new ModelMapper();

    Task toDomain(NewTaskDto newTaskDto) {
        return mapper.map(newTaskDto, Task.class);
    }

    TaskDto toDto(Task task) {
        return mapper.map(task, TaskDto.class);
    }

}
