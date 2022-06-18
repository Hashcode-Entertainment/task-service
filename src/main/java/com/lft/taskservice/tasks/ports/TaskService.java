package com.lft.taskservice.tasks.ports;

import com.lft.taskservice.tasks.domain.Task;

import java.util.List;

public interface TaskService {

    List<Task> findAll();

    Task save(Task task);

    Task findTaskById(Long taskId);

    void deleteTaskById(Long taskId);

    Task updateTask(Task task);

}
