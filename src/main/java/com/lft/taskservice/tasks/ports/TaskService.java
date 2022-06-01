package com.lft.taskservice.tasks.ports;

import com.lft.taskservice.tasks.domain.Assignment;
import com.lft.taskservice.tasks.domain.Task;

import java.util.List;

public interface TaskService {

    Task save(Task task);

    Assignment assignTaskToUser(Assignment assignment);

    List<Task> getAllTasksAssignedToUser(Long userId);

    Assignment changeDeadline(Assignment assignment);

}
