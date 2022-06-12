package com.lft.taskservice.tasks.ports;

import com.lft.taskservice.tasks.domain.Task;

public interface TaskService {

    Task save(Task task);

    Task findTaskById(Long taskId);

    void deleteTaskById(Long taskId);

}
