package com.lft.taskservice.tasks.ports;

import com.lft.taskservice.tasks.domain.Task;

public interface TaskRepository {

    Task save(Task task);

    Task findById(Long id);

    void deleteTaskById(Long taskId);

}
