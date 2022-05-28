package com.lft.taskservice.tasks.ports;

import com.lft.taskservice.tasks.domain.Task;

public interface TaskService {

    Task save(Task task);

}
