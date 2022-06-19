package com.lft.taskservice.tasks.ports;

import com.lft.taskservice.tasks.domain.Task;

import java.util.List;

public interface TaskRepository {

    List<Task> findAll();

    Task save(Task task);

    Task findById(Long id);

    void deleteTaskById(Long taskId);

    Task updateTask(Task task);
}
